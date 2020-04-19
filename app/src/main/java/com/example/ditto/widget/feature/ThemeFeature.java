package com.example.ditto.widget.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author menghaonan
 * @date 2020/07/01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ThemeFeature {
    int which();

    int FEATURE_INIT = 0x00;
    int FEATURE_SET_THEME_BACKGROUND = 0x10;
    int FEATURE_SET_THEME_TEXT_COLOR = 0x11;
    int FEATURE_SET_THEME_HINT_TEXT_COLOR = 0x12;
    int FEATURE_SET_THEME_ALPHA = 0x13;
    int FEATURE_SET_THEME_IMAGE_RES = 0x14;
    int FEATURE_SET_VECTOR_THEME_BACKGROUND = 0x15;
    int FEATURE_SET_VECTOR_THEME_IMAGE_RES = 0x16;
    int FEATURE_REMOVE_THEME_BACKGROUND = 0x20;
    int FEATURE_REMOVE_THEME_TEXT_COLOR = 0x21;
    int FEATURE_REMOVE_THEME_HINT_TEXT_COLOR = 0x22;
    int FEATURE_REMOVE_THEME_ALPHA = 0x23;
    int FEATURE_REMOVE_THEME_IMAGE_RES = 0x24;
    int FEATURE_SWITCH_THEME = 0x30;
}
