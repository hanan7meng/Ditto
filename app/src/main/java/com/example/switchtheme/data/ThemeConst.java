package com.example.switchtheme.data;

import androidx.annotation.StringDef;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class ThemeConst {
    @StringDef({
            Theme.THEME_DEFAULT,
            Theme.THEME_DARK,
            Theme.THEME_NIGHT
    })
    public @interface Theme {
        String THEME_DEFAULT = "";
        String THEME_DARK = "_dark";
        String THEME_NIGHT = "_night";
    }

    @StringDef({
            ThemeAttrs.THEME_ATTR_BACKGROUND,
            ThemeAttrs.THEME_ATTR_TEXT_COLOR,
            ThemeAttrs.THEME_ATTR_SRC,
            ThemeAttrs.THEME_ATTR_ALPHA
    })
    public @interface ThemeAttrs {
        String THEME_ATTR_BACKGROUND = "background";
        String THEME_ATTR_TEXT_COLOR = "textColor";
        String THEME_ATTR_SRC = "src";
        String THEME_ATTR_ALPHA = "alpha";
    }

    @StringDef({
            ThemeSupportView.VIEW,
            ThemeSupportView.TEXT_VIEW,
            ThemeSupportView.IMAGE_VIEW,
            ThemeSupportView.BUTTON,
            ThemeSupportView.FRAME_LAYOUT,
            ThemeSupportView.LINEAR_LAYOUT,
            ThemeSupportView.CONSTRAINT_LAYOUT,
            ThemeSupportView.RELATIVE_LAYOUT
    })
    public @interface ThemeSupportView {
        String VIEW = "View";
        String TEXT_VIEW = "TextView";
        String IMAGE_VIEW = "ImageView";
        String BUTTON = "Button";
        String FRAME_LAYOUT = "FrameLayout";
        String LINEAR_LAYOUT = "LinearLayout";
        String CONSTRAINT_LAYOUT = "androidx.constraintlayout.widget.ConstraintLayout";
        String RELATIVE_LAYOUT = "RelativeLayout";
    }
}
