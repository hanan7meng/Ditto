package com.example.switchtheme.delegate;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.switchtheme.ThemeApplication;
import com.example.switchtheme.ThemeManager;
import com.example.switchtheme.attribute.ThemeAttr;
import com.example.switchtheme.attribute.ThemeAttrUtil;
import com.example.switchtheme.data.ThemeConst.ThemeAttrs;
import com.example.switchtheme.view.IThemeView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeDelegate implements IThemeDelegate {

    private Context mContext;
    private Resources mResource;

    private volatile static IThemeDelegate sInstance;

    private ThemeDelegate() {
        mContext = ThemeApplication.getContext();
        mResource = mContext.getResources();
    }

    public static IThemeDelegate getInstance() {
        if (sInstance == null) {
            synchronized (ThemeDelegate.class) {
                if (sInstance == null) {
                    sInstance = new ThemeDelegate();
                }
            }
        }
        return sInstance;
    }

    /**
     * 从AttributeSet中取出和主题相关的属性, 封装成ThemeAttr对象并存储
     */
    @Override
    public void holdAttrs(AttributeSet attrSet, IThemeView themeView) {
        Map<String, ThemeAttr> attrs = ThemeAttrUtil.getThemeAttrs(attrSet);
        themeView.setThemeAttrs(checkAttrsEmpty(attrs));
    }

    /**
     * 根据resId和attrName, 封装成ThemeAttr对象并存储
     */
    @Override
    public ThemeAttr holdAttr(String attrName, int resId, IThemeView themeView) {
        Map<String, ThemeAttr> attrs = themeView.getThemeAttrs();
        attrs = checkAttrsEmpty(attrs);
        ThemeAttr themeAttr = ThemeAttrUtil.getThemeAttr(attrName, resId);
        attrs.put(attrName, themeAttr);
        themeView.setThemeAttrs(attrs);

        return themeAttr;
    }

    @Override
    public void switchTheme(IThemeView themeView) {
        Map<String, ThemeAttr> attrs = themeView.getThemeAttrs();
        if (attrs == null) {
            return;
        }
        View view = themeView.getView();
        String currentTheme = ThemeManager.getInstance().getCurrentTheme();
        for (ThemeAttr attr : attrs.values()) {
            // 更新对应主题的资源名
            attr.setResourceName(currentTheme);
            switchThemeByAttr(attr, view);
        }
    }

    private void switchThemeByAttr(ThemeAttr attr, View view) {
        switch (attr.getAttrName()) {
            case ThemeAttrs.THEME_ATTR_BACKGROUND:
                setBackground(attr, view);
                break;
            case ThemeAttrs.THEME_ATTR_TEXT_COLOR:
                setTextColor(attr, view);
                break;
            case ThemeAttrs.THEME_ATTR_SRC:
                setImageResource(attr, view);
                break;
            case ThemeAttrs.THEME_ATTR_ALPHA:
                setAlpha(attr, view);
                break;
            default:
        }
    }

    @Override
    public void setBackground(int resId, IThemeView themeView) {
        setBackground(holdAttr(ThemeAttrs.THEME_ATTR_BACKGROUND, resId, themeView), themeView.getView());
    }

    private void setBackground(ThemeAttr attr, View view) {
        Drawable drawable = ContextCompat.getDrawable(mContext, getRealResIdByThemeAttr(attr));
        view.setBackground(drawable);
    }

    @Override
    public void setTextColor(int resId, IThemeView themeView) {
        setTextColor(holdAttr(ThemeAttrs.THEME_ATTR_TEXT_COLOR, resId, themeView), themeView.getView());
    }

    private void setTextColor(ThemeAttr attr, View view) {
        ColorStateList color = ContextCompat.getColorStateList(mContext, getRealResIdByThemeAttr(attr));
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        }
    }

    @Override
    public void setAlpha(int resId, IThemeView themeView) {
        setAlpha(holdAttr(ThemeAttrs.THEME_ATTR_ALPHA, resId, themeView), themeView.getView());
    }

    private void setAlpha(ThemeAttr attr, View view) {
        float alpha = ResourcesCompat.getFloat(mResource, getRealResIdByThemeAttr(attr));
        view.setAlpha(alpha);
    }

    @Override
    public void setImageResource(int resId, IThemeView themeView) {
        setImageResource(holdAttr(ThemeAttrs.THEME_ATTR_SRC, resId, themeView), themeView.getView());
    }

    private void setImageResource(ThemeAttr attr, View view) {
        Drawable drawable = ContextCompat.getDrawable(mContext, getRealResIdByThemeAttr(attr));
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(drawable);
        }
    }

    /**
     * resName, resType, res所在package可以确定该res的id
     */
    private int getRealResIdByThemeAttr(ThemeAttr attr) {
        int resId = mResource.getIdentifier(attr.getResourceName(), attr.getTypeName(), attr.getPackageName());
        // 对应主题的资源id不存在的话, 那就用默认主题的
        if (resId == 0) {
            resId = mResource.getIdentifier(attr.getEntryName(), attr.getTypeName(), attr.getPackageName());
        }
        return resId;
    }

    private Map<String, ThemeAttr> checkAttrsEmpty(Map<String, ThemeAttr> attrs) {
        if (attrs == null) {
            attrs = new HashMap<>(4);
        }
        return attrs;
    }

    @Override
    public void register(IThemeView view) {
        EventBus.getDefault().register(view);
    }

    @Override
    public void unRegister(IThemeView view) {
        EventBus.getDefault().unregister(view);
    }
}
