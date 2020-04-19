package com.example.ditto.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.example.ditto.attribute.ThemeAttr;
import com.example.ditto.data.DittoMessage;
import com.example.ditto.delegate.ThemeDelegate;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/5
 */
public class ThemeRelativeLayout extends RelativeLayout implements IThemeView {

    private Map<String, ThemeAttr> mAttrs;

    public ThemeRelativeLayout(Context context) {
        super(context);
        init(null);
    }

    public ThemeRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ThemeRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    public void receiveThemeMessage(DittoMessage msg) {
        ThemeDelegate.getInstance().switchTheme(this);
    }
}
