package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseNoNetworkActivity;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import butterknife.Bind;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/5/5
 * <p>
 * 类描述：
 */

public class PDFActivity extends BaseNoNetworkActivity implements OnPageChangeListener,OnLoadCompleteListener, OnDrawListener {

    @Bind(R.id.pdfView)
    PDFView pdfView;
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, PDFActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        //从assets目录读取pdf
        displayFromAssets("sample.pdf");
    }

    private void displayFromAssets(String assetFileName ) {
        pdfView.fromAsset(assetFileName) //设置pdf文件地址
                .defaultPage(1)  //设置默认显示第1页
                .onPageChange(this) //设置翻页监听
                .onLoad(this)  //设置加载监听
                .onDraw(this)  //绘图监听
                .showMinimap(false) //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical( false ) //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true) //是否允许翻页，默认是允许翻页
//   .pages() //把 5 过滤掉
                .load();
    }

    /**
     * 翻页回调
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText( getActivity() , "page= " + page +
                " pageCount= " + pageCount , Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载完成回调
     * @param nbPages 总共的页数
     */
    @Override
    public void loadComplete(int nbPages) {
        Toast.makeText( getActivity(), "加载完成" + nbPages , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
        // Toast.makeText( MainActivity.this , "pageWidth= " + pageWidth + "
        // pageHeight= " + pageHeight + " displayedPage=" + displayedPage , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected Activity getActivity() {
        return this;
    }
}
