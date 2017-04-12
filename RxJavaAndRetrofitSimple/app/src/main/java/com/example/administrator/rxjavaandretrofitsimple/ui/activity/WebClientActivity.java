package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.qzy.library.byeburgerview.ByeBurgerBottomBehavior;
import com.android.qzy.library.byeburgerview.ByeBurgerTitleBehavior;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsResponse;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseNoNetworkActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.example.administrator.rxjavaandretrofitsimple.util.NetConnectionUtils;
import com.example.administrator.rxjavaandretrofitsimple.widget.ScrollWebView;
import com.example.administrator.rxjavaandretrofitsimple.widget.ShareDialog;

import java.io.File;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/1
 *
 * 类描述：WebView通用类(使用了WebView的离线缓存功能)
 */

public class WebClientActivity extends BaseNoNetworkActivity implements ScrollWebView.OnScrollListener,View.OnClickListener{

    private CoordinatorLayout rootLayout;
    private ScrollWebView webView;
    private ProgressBar loadingBar;
    private RelativeLayout rlTitle;
    private LinearLayout llBottomBar;
    private ImageView ivBack;
    private TextView tvTitle;
    //private String url;
    //private String title;
    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    private String cacheDirPath;
    private ImageView ivShare;
    private ShareDialog shareDialog;
    private NewsResponse.ResultBean.DataBean response;

    public static void startFrom(Activity source, NewsResponse.ResultBean.DataBean response) {
        Intent intent = new Intent(source, WebClientActivity.class);
        intent.putExtra(LocalConstant.NEWSENTITY, response);
        source.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview_client;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        ivShare = (ImageView) findViewById(R.id.ivShare);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        rlTitle = (RelativeLayout) findViewById(R.id.rlTitle);
        llBottomBar = (LinearLayout) findViewById(R.id.llBottomBar);
        webView = ((ScrollWebView) findViewById(R.id.client));
        loadingBar = ((ProgressBar) findViewById(R.id.loadingBar));
        response = (NewsResponse.ResultBean.DataBean) getIntent().getExtras().get(LocalConstant.NEWSENTITY);
        tvTitle.setText(response.title);
        initWebView();
        ivShare.setOnClickListener(this);
    }

    public void initWebView(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        // 设置缓存策略，判断是否有网络，有的网络使用LOAD_DEFAULT,无网络时使用LOAD_CACHE_ELSE_NETWORK
        if (NetConnectionUtils.isNetConnected(getActivity())) {
            //当前有可用网络
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式( 根据cache-control决定是否从网络上取数据。)
        } else {
            //当前没有可用网络
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式(只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。)
        }
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        // 设置Application caches缓存目录
        settings.setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        settings.setAppCacheEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                loadingBar.setProgress(newProgress);
                if (newProgress > 99) {
                    loadingBar.setVisibility(View.GONE);
                }
            }
        });
        webView.setListener(this);
        webView.loadUrl(response.url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivShare:
                if(null == shareDialog){
                    shareDialog = new ShareDialog(getActivity(),(View)rootLayout.getParent(),response);
                }
                shareDialog.show();
                break;
        }
    }

    public void back(View view){
        if (webView.canGoBack()) {
            webView.goBack();
        }else{
            finish();
        }
    }

    /**
     * WebView上滑回调
     */
    @Override
    public void onScrollUp() {
        ByeBurgerTitleBehavior.from(rlTitle).show();
        ByeBurgerBottomBehavior.from(llBottomBar).show();
    }

    /**
     * WebView下滑回调
     */
    @Override
    public void onScrollDown() {
        ByeBurgerTitleBehavior.from(rlTitle).hide();
        ByeBurgerBottomBehavior.from(llBottomBar).hide();
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void initTitleBar() {
        showTitle(false);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    /**
     * 重写返回键  返回WebView上个浏览界面
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }

    /**
     * 销毁时移除WebView防止内存泄漏
     */
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.removeAllViews();
            try {
                webView.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
