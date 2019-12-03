package com.example.switchtheme.delegate;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.switchtheme.ThemeApplication;
import com.example.switchtheme.ThemeManager;
import com.example.switchtheme.data.ThemeMessage;
import com.example.switchtheme.attribute.ThemeAttr;
import com.example.switchtheme.attribute.ThemeAttrUtil;
import com.example.switchtheme.data.ThemeConst.ThemeAttrs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeDelegateImpl implements IThemeDelegate {

    private Resources mResource;
    private Map<String, ThemeAttr> attrs;
    private View mView;

    public ThemeDelegateImpl(View view) {
        mView = view;
        mResource = ThemeApplication.getContext().getResources();
    }

    /**
     * xml解析的View
     * 从AttributeSet中取出和主题相关的属性, 封装成ThemeAttr对象并存储
     */
    @Override
    public void holdAttrs(AttributeSet attrSet) {
        attrs = ThemeAttrUtil.getThemeAttrs(attrSet);
        checkAttrs();
        // 初始化的时候, xml中创建的View也要更换成当前主题
        switchTheme();
    }

    /**
     * 动态创建的View
     * 根据resId和attrName, 封装成ThemeAttr对象并存储
     */
    @Override
    public ThemeAttr holdAttr(@ThemeAttrs String attrName, int resId) {
        checkAttrs();
        ThemeAttr themeAttr = ThemeAttrUtil.getThemeAttr(attrName, resId);
        attrs.put(attrName, themeAttr);
        return themeAttr;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveThemeMessage(ThemeMessage msg) {
        switchTheme();
    }

    @Override
    public void switchTheme() {
        if (attrs == null) {
            return;
        }
        String currentTheme = ThemeManager.getInstance().getCurrentTheme();
        for (ThemeAttr attr : attrs.values()) {
            // 更新对应主题的资源名
            attr.setResourceName(currentTheme);
            switchThemeByAttr(attr);
        }
    }

    private void switchThemeByAttr(ThemeAttr attr) {
        switch (attr.getAttrName()) {
            case ThemeAttrs.THEME_ATTR_BACKGROUND:
                setBackground(attr);
                break;
            case ThemeAttrs.THEME_ATTR_TEXT_COLOR:
                setTextColor(attr);
                break;
            case ThemeAttrs.THEME_ATTR_SRC:
                setImageResource(attr);
                break;
            case ThemeAttrs.THEME_ATTR_ALPHA:
                setAlpha(attr);
                break;
            default:
        }
    }

    @Override
    public void setBackground(int resId) {
        setBackground(holdAttr(ThemeAttrs.THEME_ATTR_BACKGROUND, resId));
    }

    private void setBackground(ThemeAttr attr) {
        Drawable drawable = mResource.getDrawable(getRealResIdByThemeAttr(attr), null);
        mView.setBackground(drawable);
    }

    @Override
    public void setTextColor(int resId) {
        setTextColor(holdAttr(ThemeAttrs.THEME_ATTR_TEXT_COLOR, resId));
    }

    private void setTextColor(ThemeAttr attr) {
        int color = mResource.getColor(getRealResIdByThemeAttr(attr), null);
        if (mView instanceof TextView) {
            ((TextView) mView).setTextColor(color);
        }
    }

    @Override
    public void setAlpha(int resId) {
        setAlpha(holdAttr(ThemeAttrs.THEME_ATTR_ALPHA, resId));
    }

    private void setAlpha(ThemeAttr attr) {
        float alpha = ResourcesCompat.getFloat(mResource, getRealResIdByThemeAttr(attr));
        mView.setAlpha(alpha);
    }

    @Override
    public void setImageResource(int resId) {
        setImageResource(holdAttr(ThemeAttrs.THEME_ATTR_SRC, resId));
    }

    private void setImageResource(ThemeAttr attr) {
        Drawable drawable = mResource.getDrawable(getRealResIdByThemeAttr(attr), null);
        if (mView instanceof ImageView) {
            ((ImageView) mView).setImageDrawable(drawable);
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

    private void checkAttrs() {
        if (attrs == null) {
            attrs = new HashMap<>(4);
        }
    }

    @Override
    public void register() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegister() {
        EventBus.getDefault().unregister(this);
    }
}
