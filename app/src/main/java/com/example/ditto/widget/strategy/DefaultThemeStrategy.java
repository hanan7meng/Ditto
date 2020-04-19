package com.example.ditto.widget.strategy;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.ditto.DittoApplication;
import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.widget.view.IThemeView;

/**
 * 没有换肤功能
 *
 * @author menghaonan
 * @date 2020/05/03
 */
public class DefaultThemeStrategy implements IThemeStrategy {

    private Context mContext;
    private Resources mRes;

    public DefaultThemeStrategy() {
        mContext = DittoApplication.getAppContext();
        mRes = DittoApplication.getAppResources();
    }

    @Override
    public void setBackground(ThemeAttr attr, IThemeView themeView) {
        View view = themeView.getView();
        view.setBackgroundResource(attr.getDefaultResId());
    }

    @Override
    public void setTextColor(ThemeAttr attr, IThemeView themeView) {
        View view = themeView.getView();
        if (!(view instanceof TextView)) {
            return;
        }
        ColorStateList color = ContextCompat.getColorStateList(mContext, attr.getDefaultResId());
        ((TextView) view).setTextColor(color);
    }

    @Override
    public void setAlpha(ThemeAttr attr, IThemeView themeView) {
        View view = themeView.getView();
        float alpha = ResourcesCompat.getFloat(mRes, attr.getDefaultResId());
        view.setAlpha(alpha);
    }

    @Override
    public void setImageResource(ThemeAttr attr, IThemeView themeView) {
        View view = themeView.getView();
        if (!(view instanceof ImageView)) {
            return;
        }
        ((ImageView) view).setImageResource(attr.getDefaultResId());
    }

    @Override
    public void setHintTextColor(ThemeAttr attr, IThemeView themeView) {
        View view = themeView.getView();
        if (!(view instanceof EditText)) {
            return;
        }
        ColorStateList color = ContextCompat.getColorStateList(mContext, attr.getDefaultResId());
        ((EditText) view).setHintTextColor(color);
    }
}
