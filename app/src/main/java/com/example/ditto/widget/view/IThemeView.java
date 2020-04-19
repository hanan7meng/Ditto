package com.example.ditto.widget.view;

import android.view.View;

import com.example.ditto.event.DittoEvent;
import com.example.ditto.model.DittoConst.Theme;
import com.example.ditto.model.attr.ThemeAttrsHolder;
import com.example.ditto.utils.ThemeTagUtil;
import com.example.ditto.widget.strategy.IThemeStrategy;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public interface IThemeView {

    void setThemeAttrsHolder(ThemeAttrsHolder holder);

    ThemeAttrsHolder getThemeAttrsHolder();

    View getView();

    void switchTheme(DittoEvent msg);

    void switchTheme(@Theme String selfTheme);

    default IThemeStrategy getStrategy() {
        return ThemeTagUtil.getThemeStrategy(getView());
    }

    default void setStrategy(IThemeStrategy strategy) {
        ThemeTagUtil.setThemeStrategy(strategy, getView());
    }

    default void setThemeEnabled(boolean enabled) {
        ThemeTagUtil.setThemeEnabled(enabled, getView());
    }

    default boolean isThemeEnabled() {
        return ThemeTagUtil.isThemeEnabled(getView());
    }

    default void setCustomTheme(@Theme String customTheme) {
        ThemeTagUtil.setCustomTheme(customTheme, getView());
    }

    default String getSelfTheme() {
        return ThemeTagUtil.getCustomTheme(getView());
    }
}
