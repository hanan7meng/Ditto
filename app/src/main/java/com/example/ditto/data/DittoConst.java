package com.example.ditto.data;

import androidx.annotation.StringDef;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class DittoConst {
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
            ThemeAttrs.THEME_ATTR_ALPHA,
            ThemeAttrs.THEME_ATTR_STYLE,
            ThemeAttrs.THEME_ATTR_TEXT_COLOR_HINT
    })
    public @interface ThemeAttrs {
        String THEME_ATTR_BACKGROUND = "background";
        String THEME_ATTR_TEXT_COLOR = "textColor";
        String THEME_ATTR_SRC = "src";
        String THEME_ATTR_ALPHA = "alpha";
        String THEME_ATTR_STYLE = "style";
        String THEME_ATTR_TEXT_COLOR_HINT = "textColorHint";
    }

    @StringDef({
            DittoSupportView.VIEW,
            DittoSupportView.TEXT_VIEW,
            DittoSupportView.IMAGE_VIEW,
            DittoSupportView.BUTTON,
            DittoSupportView.FRAME_LAYOUT,
            DittoSupportView.LINEAR_LAYOUT,
            DittoSupportView.CONSTRAINT_LAYOUT,
            DittoSupportView.RELATIVE_LAYOUT,
            DittoSupportView.EDIT_TEXT
    })
    public @interface DittoSupportView {
        String VIEW = "View";
        String TEXT_VIEW = "TextView";
        String IMAGE_VIEW = "ImageView";
        String BUTTON = "Button";
        String FRAME_LAYOUT = "FrameLayout";
        String LINEAR_LAYOUT = "LinearLayout";
        String CONSTRAINT_LAYOUT = "androidx.constraintlayout.widget.ConstraintLayout";
        String RELATIVE_LAYOUT = "RelativeLayout";
        String EDIT_TEXT = "EditText";
    }

    @StringDef({
            ResType.TYPE_COLOR,
            ResType.TYPE_DRAWABLE
    })
    public @interface ResType {
        String TYPE_COLOR = "color";
        String TYPE_DRAWABLE = "drawable";
    }
}
