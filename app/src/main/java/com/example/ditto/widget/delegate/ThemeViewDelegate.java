package com.example.ditto.widget.delegate;

import com.example.ditto.DittoManager;
import com.example.ditto.model.DittoConst.Attrs;
import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.widget.strategy.DefaultThemeStrategy;
import com.example.ditto.widget.strategy.DittoThemeStrategy;
import com.example.ditto.widget.strategy.IThemeStrategy;
import com.example.ditto.widget.view.IThemeView;

import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeViewDelegate implements IThemeViewDelegate {

    private IThemeStrategy mDefaultStrategy;
    private IThemeStrategy mDittoStrategy;

    private volatile static IThemeViewDelegate sInstance;

    private ThemeViewDelegate() {
        mDefaultStrategy = new DefaultThemeStrategy();
        mDittoStrategy = new DittoThemeStrategy();
    }

    public static IThemeViewDelegate getInstance() {
        if (sInstance == null) {
            synchronized (ThemeViewDelegate.class) {
                if (sInstance == null) {
                    sInstance = new ThemeViewDelegate();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void switchTheme(IThemeView themeView) {
        if (!themeView.isThemeEnabled()) {
            return;
        }
        String currentTheme = themeView.getSelfTheme();
        if (currentTheme == null) {
            currentTheme = DittoManager.getInstance().getCurrentTheme();
        }
        switchTheme(themeView, currentTheme);
    }

    @Override
    public void switchTheme(IThemeView themeView, String theme) {
        Map<String, ThemeAttr> attrs = themeView.getThemeAttrsHolder().getThemeAttrs();
        if (attrs == null) {
            return;
        }
        for (ThemeAttr attr : attrs.values()) {
            attr.setTheme(theme);
            setThemeAttr(themeView, attr);
        }
    }

    @Override
    public void setThemeAttr(IThemeView themeView, ThemeAttr attr) {
        IThemeStrategy strategy = getThemeStrategy(themeView);
        switch (attr.getAttrName()) {
            case Attrs.BACKGROUND:
                strategy.setBackground(attr, themeView);
                break;
            case Attrs.TEXT_COLOR:
                strategy.setTextColor(attr, themeView);
                break;
            case Attrs.SRC:
                strategy.setImageResource(attr, themeView);
                break;
            case Attrs.ALPHA:
                strategy.setAlpha(attr, themeView);
                break;
            case Attrs.TEXT_COLOR_HINT:
                strategy.setHintTextColor(attr, themeView);
                break;
            default:
        }
    }

    @Override
    public void removeThemeAttr(IThemeView themeView, String attrName) {
        themeView.getThemeAttrsHolder().getThemeAttrs().remove(attrName);
    }

    private IThemeStrategy getThemeStrategy(IThemeView themeView) {
        if (!themeView.isThemeEnabled()) {
            return mDefaultStrategy;
        }
        IThemeStrategy selfStrategy = themeView.getStrategy();
        if (selfStrategy != null) {
            return selfStrategy;
        }
        return mDittoStrategy;
    }
}
