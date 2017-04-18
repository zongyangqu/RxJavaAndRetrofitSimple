package com.example.administrator.rxjavaandretrofitsimple.util.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/19
 *
 * 类描述：
 */

public class ToolbarWrapper extends Toolbar {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.leftTextButton)
    TextView leftTextButton;
    @Bind(R.id.rightTextButton)
    TextView rightTextButton;
    @Bind(R.id.realToolbar)
    Toolbar realToolbar;
    @Bind(R.id.rightImageButton)
    ImageView rightImageButton;

    private static final int DEFAULT_COLOR = Color.parseColor("#ca2729");//App theme color
    private static final int DEFAULT_LEFT_TEXT_BUTTON_COLOR = DEFAULT_COLOR;
    private static final int DEFAULT_RIGHT_TEXT_BUTTON_COLOR = DEFAULT_COLOR;
    private static final int DEFAULT_TITLE_COLOR = Color.WHITE;
    private static final int DEFAULT_BKG_COLOR = Color.WHITE;

    public ToolbarWrapper(Context context) {
        this(context, null);
    }

    public ToolbarWrapper(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolbarWrapper(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_toolbar_wrapper, this, true);
        ButterKnife.bind(this);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ToolbarWrapper);
        if (ta != null) {

            Drawable navigationIconDrawable = ta.getDrawable(R.styleable.ToolbarWrapper_tbw_navigation_icon);
            if (navigationIconDrawable != null) {
                setNavigationIcon(navigationIconDrawable);
            }

            boolean navigationEnable = ta.getBoolean(R.styleable.ToolbarWrapper_tbw_navigation_enable, true);
            if (!navigationEnable) {
                setNavigationIcon(null);
            }

            String leftTextButtonText = ta.getString(R.styleable.ToolbarWrapper_tbw_left_text);
            if (!TextUtils.isEmpty(leftTextButtonText)) {
                setLeftButtonText(leftTextButtonText);
            } else {
                leftTextButton.setVisibility(View.GONE);
            }
            leftTextButton.setTextColor(ta.getColor(R.styleable.ToolbarWrapper_tbw_left_text_color, DEFAULT_LEFT_TEXT_BUTTON_COLOR));

            String rightTextButtonText = ta.getString(R.styleable.ToolbarWrapper_tbw_right_text);
            if (!TextUtils.isEmpty(rightTextButtonText)) {
                setRightButtonText(rightTextButtonText);
            } else {
                rightTextButton.setVisibility(View.GONE);
            }
            rightTextButton.setTextColor(ta.getColor(R.styleable.ToolbarWrapper_tbw_right_text_color, DEFAULT_RIGHT_TEXT_BUTTON_COLOR));

            String title = ta.getString(R.styleable.ToolbarWrapper_tbw_title);
            if (title != null) {
                setCenterTitle(title);
            }
            this.title.setTextColor(ta.getColor(R.styleable.ToolbarWrapper_tbw_title_color, DEFAULT_TITLE_COLOR));

            Drawable rightImageButtonDrawable = ta.getDrawable(R.styleable.ToolbarWrapper_tbw_right_image_button_src);
            if (rightImageButtonDrawable != null) {
                rightImageButton.setVisibility(View.VISIBLE);
                rightTextButton.setVisibility(View.GONE);
                rightImageButton.setImageDrawable(rightImageButtonDrawable);
            } else {
                rightImageButton.setVisibility(View.GONE);
            }

            int bkgColor = ta.getColor(R.styleable.ToolbarWrapper_tbw_background_color, DEFAULT_BKG_COLOR);
            setBackgroundColor(bkgColor);

            ta.recycle();
        }
    }

    @Override
    public void setNavigationIcon(@DrawableRes int resId) {
        realToolbar.setNavigationIcon(resId);
    }

    public void setTitleBarBackGroundColor(String color){
        this.realToolbar.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void setNavigationIcon(@Nullable Drawable icon) {
        realToolbar.setNavigationIcon(icon);
    }

    @Override
    public void setNavigationOnClickListener(OnClickListener listener) {
        realToolbar.setNavigationOnClickListener(listener);
    }

    public void setTitleColor(int color) {
        this.title.setTextColor(color);
    }
    public void setTitleColor(String color) {
        this.title.setTextColor(Color.parseColor(color));
    }

    public void setLeftButtonText(String text) {
        if (!TextUtils.isEmpty(text)) {
            setNavigationIcon(null);
            leftTextButton.setText(text);
            leftTextButton.setVisibility(View.VISIBLE);
        }
    }

    public void setRightButtonText(String text) {
        if (!TextUtils.isEmpty(text)) {
            rightTextButton.setText(text);
            rightTextButton.setVisibility(View.VISIBLE);
        }
    }

    public void setCenterTitle(String title) {
        this.title.setVisibility(View.VISIBLE);
        this.title.setText(title);
    }

    public TextView getCenterTitle() {
        return title;
    }

    public ImageView getRightImageButton() {
        return rightImageButton;
    }

    public TextView getRightTextButton() {
        return rightTextButton;
    }
}
