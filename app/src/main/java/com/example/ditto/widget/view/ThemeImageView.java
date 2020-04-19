package com.example.ditto.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.ditto.event.DittoEvent;
import com.example.ditto.model.attr.ThemeAttrsHolder;
import com.example.ditto.widget.feature.ThemeFeature;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeImageView extends AppCompatImageView implements IThemeView {

    private ThemeAttrsHolder mAttrsHolder;

    public ThemeImageView(Context context) {
        this(context, null);
    }

    public ThemeImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThemeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    @ThemeFeature(which = ThemeFeature.FEATURE_SET_THEME_IMAGE_RES)
    public void setThemeImageResource(@DrawableRes int resId) {

    }

    @ThemeFeature(which = ThemeFeature.FEATURE_SET_VECTOR_THEME_IMAGE_RES)
    public void setThemeImageResource(@DrawableRes int resId, @ColorRes int vectorColorId) {

    }

    @ThemeFeature(which = ThemeFeature.FEATURE_REMOVE_THEME_IMAGE_RES)
    public void removeThemeImageResource() {

    }

    @ThemeFeature(which = ThemeFeature.FEATURE_SET_THEME_ALPHA)
    public void setThemeAlpha(@DimenRes int resId) {

    }

    @ThemeFeature(which = ThemeFeature.FEATURE_REMOVE_THEME_ALPHA)
    public void removeThemeAlpha() {

    }
}
