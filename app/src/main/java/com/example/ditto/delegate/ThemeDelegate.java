package com.example.ditto.delegate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.ditto.DittoApplication;
import com.example.ditto.DittoManager;
import com.example.ditto.attribute.ThemeAttr;
import com.example.ditto.utils.ThemeAttrUtil;
import com.example.ditto.data.DittoConst.ThemeAttrs;
import com.example.ditto.view.IThemeView;
import com.example.ditto.view.wrapper.ThemeEditTextWrapper;
import com.example.ditto.view.wrapper.ThemeImageViewWrapper;
import com.example.ditto.view.wrapper.ThemeTextViewWrapper;
import com.example.ditto.view.wrapper.ThemeViewWrapper;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeDelegate implements IThemeDelegate {

    private Context mContext;

    private ThemeViewWrapper mView;
    private ThemeImageViewWrapper mImageView;
    private ThemeTextViewWrapper mTextView;
    private ThemeEditTextWrapper mEditText;

    private volatile static IThemeDelegate sInstance;

    private ThemeDelegate() {
        mContext = DittoApplication.getContext();

        mView = new ThemeViewWrapper(mContext);
        mImageView = new ThemeImageViewWrapper(mContext);
        mTextView = new ThemeTextViewWrapper(mContext);
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
        String currentTheme = DittoManager.getInstance().getCurrentTheme();
        for (ThemeAttr attr : attrs.values()) {
            // 更新对应主题的资源名
            attr.setResourceName(currentTheme);
            switchThemeByAttr(attr, view);
        }
    }

    private void switchThemeByAttr(ThemeAttr attr, View view) {
        switch (attr.getAttrName()) {
            case ThemeAttrs.THEME_ATTR_BACKGROUND:
                mView.setBackground(attr, view);
                break;
            case ThemeAttrs.THEME_ATTR_TEXT_COLOR:
                mTextView.setTextColor(attr, view);
                break;
            case ThemeAttrs.THEME_ATTR_SRC:
                mImageView.setImageResource(attr, view);
                break;
            case ThemeAttrs.THEME_ATTR_ALPHA:
                mView.setAlpha(attr, view);
                break;
            default:
        }
    }

    @Override
    public void setBackground(int resId, IThemeView themeView) {
        mView.setBackground(holdAttr(ThemeAttrs.THEME_ATTR_BACKGROUND, resId, themeView), themeView.getView());
    }

    @Override
    public void setTextColor(int resId, IThemeView themeView) {
        mTextView.setTextColor(holdAttr(ThemeAttrs.THEME_ATTR_TEXT_COLOR, resId, themeView), themeView.getView());
    }

    @Override
    public void setAlpha(int resId, IThemeView themeView) {
        mView.setAlpha(holdAttr(ThemeAttrs.THEME_ATTR_ALPHA, resId, themeView), themeView.getView());
    }

    @Override
    public void setImageResource(int resId, IThemeView themeView) {
        mImageView.setImageResource(holdAttr(ThemeAttrs.THEME_ATTR_SRC, resId, themeView), themeView.getView());
    }

    @Override
    public void setHintTextColor(int resId, IThemeView themeView) {
        mEditText.setHintTextColor(holdAttr(ThemeAttrs.THEME_ATTR_TEXT_COLOR_HINT, resId, themeView), themeView.getView());
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
