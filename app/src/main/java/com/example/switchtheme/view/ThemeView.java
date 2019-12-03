package com.example.switchtheme.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.switchtheme.delegate.IThemeDelegate;
import com.example.switchtheme.delegate.ThemeDelegateImpl;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeView extends View implements IThemeView {
    private IThemeDelegate mDelegate;

    public ThemeView(Context context) {
        super(context);
        init(null);
    }

    public ThemeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ThemeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ThemeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override
    public void init(AttributeSet attrSet) {
        mDelegate = new ThemeDelegateImpl(this);
        if (attrSet != null) {
            mDelegate.holdAttrs(attrSet);
        }
    }

    public void setThemeBackground(int resId) {
        mDelegate.setBackground(resId);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mDelegate.register();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mDelegate.unRegister();
    }
}
