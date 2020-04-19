package com.example.ditto.widget.wrapper;

import android.content.Context;
import android.content.res.Resources;

import com.example.ditto.DittoApplication;
import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.model.cache.IDittoResLoader;
import com.example.ditto.model.cache.NoCacheResLoader;
import com.example.ditto.DittoManager;
import com.example.ditto.utils.ThemeResIdUtil;

/**
 * @author menghaonan
 * @date 2020/3/20
 */
public class BaseThemeViewWrapper {

    protected Context mContext;
    protected Resources mResource;

    public BaseThemeViewWrapper() {
        mContext = DittoApplication.getAppContext();
        mResource = DittoApplication.getAppResources();
    }

    protected int getRealResId(ThemeAttr attr) {
        return ThemeResIdUtil.getRealResId(attr);
    }

    protected int getResId(ThemeAttr attr) {
        return ThemeResIdUtil.getResId(attr);
    }

    protected String getCurrentTheme() {
        return DittoManager.getInstance().getCurrentTheme();
    }

    protected IDittoResLoader getResLoader() {
        IDittoResLoader loader = DittoManager.getInstance().getResLoader();
        if (loader == null) {
            loader = new NoCacheResLoader();
        }
        return loader;
    }
}
