package com.example.administrator.rxjavaandretrofitsimple.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/5/8
 * <p>
 * 类描述：
 */

public class AsImageTextView extends RelativeLayout implements View.OnClickListener {
    private ImageView ivImagetext;
    private TextView tvImagetext;

    //实例化时使用
    public AsImageTextView(Context context) {
        super(context);
    }

    //XML文件中定义使用
    public AsImageTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //使用默认样式
    public AsImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.asimagetextview, this);
        ivImagetext = ((ImageView) findViewById(R.id.iv_imagetext));
        tvImagetext = ((TextView) findViewById(R.id.tv_imagetext));
        initattrs();
        ivImagetext.setOnClickListener(this);
        tvImagetext.setOnClickListener(this);
    }

    private void initattrs() {
        //如果需要静态设置，此处可拓展
    }

    public void setIvImagetext(int imageid) {
        Drawable drawable = getResources().getDrawable(imageid);
        if (drawable != null) {
            ivImagetext.setImageDrawable(drawable);
        }
    }

    public void setTvImagetext(String typename) {
        if (typename != null) {
            tvImagetext.setText(typename + "");
        }
    }

    @Override
    public void onClick(View view) {
        if (imagetextclick != null) {
            imagetextclick.setImagetextclick();
        }
    }

    public interface Imagetextclick {
        public void setImagetextclick();
    }

    public Imagetextclick imagetextclick;
    public Imagetextclick getImagetextclic() {
        return imagetextclick;
    }
    public void setImagetextclick(Imagetextclick imagetextclick) {
        this.imagetextclick = imagetextclick;
    }
}
