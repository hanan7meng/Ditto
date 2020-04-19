package com.example.ditto.event;

import com.example.ditto.model.DittoConst.Theme;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class DittoEvent {

    private @Theme
    String theme;

    public DittoEvent() {
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(@Theme String theme) {
        this.theme = theme;
    }
}
