package com.example.administrator.rxjavaandretrofitsimple.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.qzy.library.flycodialog.BaseAnimatorSet;
import com.android.qzy.library.flycodialog.BottomBaseDialog;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsResponse;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.ViewFindUtils;
import com.nineoldandroids.animation.ObjectAnimator;
import com.tencent.connect.share.QQShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/11
 *
 * 类描述：分享提示框
 */

public class ShareDialog extends BottomBaseDialog<ShareDialog> {
    private TextView tvWechatFriendCircle;
    private TextView tvWechatFriend;
    private TextView tvQq;
    private TextView tvSms;
    private Context mContext;
    NewsResponse.ResultBean.DataBean response;


    public ShareDialog(Context context, View animateView,NewsResponse.ResultBean.DataBean response) {
        super(context, animateView);
        this.mContext = context;
        this.response = response;

    }

    public ShareDialog(Context context,NewsResponse.ResultBean.DataBean response) {
        super(context);
        this.mContext = context;
        this.response = response;
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(context, R.layout.dialog_share, null);
        tvWechatFriendCircle = ViewFindUtils.find(inflate, R.id.tvWechatFriendCircle);
        tvWechatFriend = ViewFindUtils.find(inflate, R.id.tvWechatFriend);
        tvQq = ViewFindUtils.find(inflate, R.id.tvQq);
        tvSms = ViewFindUtils.find(inflate, R.id.tvSms);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        tvWechatFriendCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbToastUtil.showToast(context,"朋友圈");
                dismiss();
            }
        });
        tvWechatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbToastUtil.showToast(context, "微信");
                dismiss();
            }
        });
        tvQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share2QQ();
                dismiss();
            }
        });
        tvSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbToastUtil.showToast(context, "短信");
                dismiss();
            }
        });
    }

    /**
     * 分享到QQ
     */
    public void share2QQ(){
        //调用QQ分享SDK
        final Tencent tencent = Tencent.createInstance("222222", mContext);
        final Bundle params = new Bundle();
        params.putString(QQShare.SHARE_TO_QQ_TITLE, response.category);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, response.url);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, response.title);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, response.thumbnail_pic_s);

        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != tencent) {
                    tencent.shareToQQ((Activity) mContext, params, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            AbToastUtil.showToast(mContext, "" + "分享成功");
                        }
                        @Override
                        public void onError(UiError uiError) {
                            AbToastUtil.showToast(mContext, "" + "分享失败");
                        }
                        @Override
                        public void onCancel() {
                            AbToastUtil.showToast(mContext, "" + "取消分析");
                        }
                    });
                }
            }
        });
    }

    private BaseAnimatorSet windowInAs;
    private BaseAnimatorSet windowOutAs;

    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (windowInAs == null) {
            windowInAs = new WindowsInAs();
        }
        return windowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (windowOutAs == null) {
            windowOutAs = new WindowsOutAs();
        }
        return windowOutAs;
    }

    class WindowsInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", 0, -0.1f * dm.heightPixels).setDuration(350)
            );
        }
    }

    class WindowsOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", -0.1f * dm.heightPixels, 0).setDuration(350)
            );
        }
    }

    public interface OnPackageListener {
        void onPackageClick();
    }
}
