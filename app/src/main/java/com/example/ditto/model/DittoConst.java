package com.example.ditto.model;

import androidx.annotation.StringDef;

/**
 * 常量类
 *
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
            Attrs.BACKGROUND,
            Attrs.TEXT_COLOR,
            Attrs.SRC,
            Attrs.ALPHA,
            Attrs.STYLE,
            Attrs.TEXT_COLOR_HINT
    })
    public @interface Attrs {
        String BACKGROUND = "background";
        String TEXT_COLOR = "textColor";
        String SRC = "src";
        String ALPHA = "alpha";
        String STYLE = "style";
        String TEXT_COLOR_HINT = "textColorHint";
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
            ResType.COLOR,
            ResType.DRAWABLE
    })
    public @interface ResType {
        String COLOR = "color";
        String DRAWABLE = "drawable";
    }
}
