package com.example.ditto.data;

import com.example.ditto.data.DittoConst.Theme;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class DittoMessage {

    private @Theme
    String theme;

    public DittoMessage() {
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(@Theme String theme) {
        this.theme = theme;
    }
}
