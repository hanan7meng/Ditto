package com.example.ditto.widget.strategy;

import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.widget.view.IThemeView;
import com.example.ditto.widget.wrapper.ThemeEditTextWrapper;
import com.example.ditto.widget.wrapper.ThemeImageViewWrapper;
import com.example.ditto.widget.wrapper.ThemeTextViewWrapper;
import com.example.ditto.widget.wrapper.ThemeViewWrapper;

/**
 * Ditto默认的换肤策略实现
 *
 * @author menghaonan
 * @date 2020/05/01
 */
public class DittoThemeStrategy implements IThemeStrategy {

    private ThemeViewWrapper mView;
    private ThemeImageViewWrapper mImageView;
    private ThemeTextViewWrapper mTextView;
    private ThemeEditTextWrapper mEditText;

    public DittoThemeStrategy() {
        mView = new ThemeViewWrapper();
        mImageView = new ThemeImageViewWrapper();
        mTextView = new ThemeTextViewWrapper();
        mEditText = new ThemeEditTextWrapper();
    }

    @Override
    public void setBackground(ThemeAttr attr, IThemeView themeView) {
        mView.setBackground(attr, themeView.getView());
    }

    @Override
    public void setTextColor(ThemeAttr attr, IThemeView themeView) {
        mTextView.setTextColor(attr, themeView.getView());
    }

    @Override
    public void setAlpha(ThemeAttr attr, IThemeView themeView) {
        mView.setAlpha(attr, themeView.getView());
    }

    @Override
    public void setImageResource(ThemeAttr attr, IThemeView themeView) {
        mImageView.setImageResource(attr, themeView.getView());
    }

    @Override
    public void setHintTextColor(ThemeAttr attr, IThemeView themeView) {
        mEditText.setHintTextColor(attr, themeView.getView());
    }
}
