package com.example.switchtheme.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.switchtheme.attribute.ThemeAttr;
import com.example.switchtheme.data.ThemeMessage;
import com.example.switchtheme.delegate.ThemeDelegate;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/5
 */
public class ThemeButton extends AppCompatButton implements IThemeView {

    private Map<String, ThemeAttr> mAttrs;

    public ThemeButton(Context context) {
        super(context);
        init(null);
    }

    public ThemeButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ThemeButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    public void setThemeTextColor(@ColorRes int resId) {
        ThemeDelegate.getInstance().setTextColor(resId, this);
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
