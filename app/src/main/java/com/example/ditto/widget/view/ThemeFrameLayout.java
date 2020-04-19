package com.example.ditto.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.example.ditto.event.DittoEvent;
import com.example.ditto.model.attr.ThemeAttrsHolder;
import com.example.ditto.widget.feature.ThemeFeature;

/**
 * @author menghaonan
 * @date 2019/12/5
 */
public class ThemeFrameLayout extends FrameLayout implements IThemeView {

    private ThemeAttrsHolder mAttrsHolder;

    public ThemeFrameLayout(Context context) {
        this(context, null);
    }

    public ThemeFrameLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThemeFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @ThemeFeature(which = ThemeFeature.FEATURE_INIT)
    private void init(AttributeSet attrSet) {

    }

    @Override
    public void setThemeAttrsHolder(ThemeAttrsHolder holder) {
        mAttrsHolder = holder;
    }

    @Override
    public ThemeAttrsHolder getThemeAttrsHolder() {
        return mAttrsHolder;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    @ThemeFeature(which = ThemeFeature.FEATURE_SWITCH_THEME)
    public void switchTheme(DittoEvent msg) {

    }

    @Override
    @ThemeFeature(which = ThemeFeature.FEATURE_SWITCH_THEME)
    public void switchTheme(String selfTheme) {

    }

    @ThemeFeature(which = ThemeFeature.FEATURE_SET_THEME_BACKGROUND)
    public void setThemeBackground(@DrawableRes int resId) {

    }

    @ThemeFeature(which = ThemeFeature.FEATURE_SET_VECTOR_THEME_BACKGROUND)
    public void setThemeBackground(@DrawableRes int resId, @ColorRes int vectorColorId) {

    }

    @ThemeFeature(which = ThemeFeature.FEATURE_REMOVE_THEME_BACKGROUND)
    public void removeThemeBackground() {

    }
}
