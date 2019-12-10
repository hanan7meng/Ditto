package com.example.switchtheme.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.example.switchtheme.attribute.ThemeAttr;
import com.example.switchtheme.data.ThemeMessage;
import com.example.switchtheme.delegate.ThemeDelegate;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeView extends View implements IThemeView {

    private Map<String, ThemeAttr> mAttrs;

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
        if (attrSet != null) {
            ThemeDelegate.getInstance().holdAttrs(attrSet, this);
        }
    }

    @Override
    public Map<String, ThemeAttr> getThemeAttrs() {
        return mAttrs;
    }

    @Override
    public void setThemeAttrs(Map<String, ThemeAttr> attrs) {
        mAttrs = attrs;
    }

    @Override
    public View getView() {
        return this;
    }

    public void setThemeBackground(@DrawableRes int resId) {
        ThemeDelegate.getInstance().setBackground(resId, this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ThemeDelegate.getInstance().register(this);
        ThemeDelegate.getInstance().switchTheme(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ThemeDelegate.getInstance().unRegister(this);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveThemeMessage(ThemeMessage msg) {
        ThemeDelegate.getInstance().switchTheme(this);
    }
}
