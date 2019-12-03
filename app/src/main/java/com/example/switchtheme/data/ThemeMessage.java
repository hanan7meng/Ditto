package com.example.switchtheme.data;

import com.example.switchtheme.data.ThemeConst.Theme;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class ThemeMessage {

    private @Theme
    String theme;

    public ThemeMessage() {
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(@Theme String theme) {
        this.theme = theme;
    }
}
