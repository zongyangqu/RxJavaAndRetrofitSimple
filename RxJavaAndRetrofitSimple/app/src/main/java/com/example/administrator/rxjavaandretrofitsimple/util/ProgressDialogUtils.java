package com.example.administrator.rxjavaandretrofitsimple.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：Loding框
 */

public class ProgressDialogUtils {

    private static LocalProgressDialog progressDialog = null;

    public static class LocalProgressDialog extends Dialog {

        private TextView message;

        @SuppressLint("InflateParams")
        public LocalProgressDialog(Activity context) {
            super(context, R.style.ProgressDialogStyle);
            View root = LayoutInflater.from(context).inflate(R.layout.dialog_progressing, null);
            message = (TextView) root.findViewById(R.id.progressMessage);
            setContentView(root, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        public LocalProgressDialog(Activity context, String message) {
            this(context);
            setMessage(message);
        }

        public LocalProgressDialog(Activity context, int messageResId) {
            this(context);
            setMessage(messageResId);
        }

        public void setMessage(String message) {
            this.message.setVisibility(View.VISIBLE);
            this.message.setText(message);
        }

        public void setMessage(int messageResId) {
            try {
                this.message.setVisibility(View.VISIBLE);
                this.message.setText(messageResId);
            } catch (Resources.NotFoundException e) {
                this.message.setVisibility(View.GONE);
            }
        }

    }

    public static void show(Activity activity) {
        synchronized (ProgressDialogUtils.class) {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    if (progressDialog.getOwnerActivity() == activity) {
                        return;
                    }
                    try {
                        progressDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                progressDialog = null;
            }
            progressDialog = new LocalProgressDialog(activity);
            progressDialog.show();
        }
    }

    public static void show(Activity activity, String message) {
        synchronized (ProgressDialogUtils.class) {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    if (progressDialog.getOwnerActivity() == activity) {
                        return;
                    }
                    try {
                        progressDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                progressDialog = null;
            }
            progressDialog = new LocalProgressDialog(activity, message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public static void show(Activity activity, int msgId) {
        synchronized (ProgressDialogUtils.class) {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    if (progressDialog.getOwnerActivity() == activity) {
                        return;
                    }
                    try {
                        progressDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                progressDialog = null;
            }
            progressDialog = new LocalProgressDialog(activity, msgId);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public static void dismiss() {
        synchronized (ProgressDialogUtils.class) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
