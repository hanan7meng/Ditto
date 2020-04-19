package com.example.ditto.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.ditto.model.DittoConst.DittoSupportView;
import com.example.ditto.widget.view.ThemeButton;
import com.example.ditto.widget.view.ThemeConstraintLayout;
import com.example.ditto.widget.view.ThemeEditText;
import com.example.ditto.widget.view.ThemeFrameLayout;
import com.example.ditto.widget.view.ThemeImageView;
import com.example.ditto.widget.view.ThemeLinearLayout;
import com.example.ditto.widget.view.ThemeRelativeLayout;
import com.example.ditto.widget.view.ThemeTextView;
import com.example.ditto.widget.view.ThemeView;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class DittoViewInflater {

    private volatile static DittoViewInflater sInstance;

    private static final String NAME_SPACE = "http://schemas.android.com/apk/res-auto";

    private DittoViewInflater() {}

    public static DittoViewInflater getInstance() {
        if (sInstance == null) {
            synchronized (DittoViewInflater.class) {
                if (sInstance == null) {
                    sInstance = new DittoViewInflater();
                }
            }
        }
        return sInstance;
    }

    public LayoutInflater.Factory2 getLayoutInflaterFactory(AppCompatDelegate delegate) {
        return new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return delegate.createView(null, name, context, attrs);
            }

            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                boolean enable = attrs.getAttributeBooleanValue(NAME_SPACE, "ditto_enable", false);
                if (!enable) {
                    return delegate.createView(parent, name, context, attrs);
                }

                View view;
                switch (name) {
                    case DittoSupportView.VIEW:
                        view = new ThemeView(context, attrs);
                        break;
                    case DittoSupportView.TEXT_VIEW:
                        view = new ThemeTextView(context, attrs);
                        break;
                    case DittoSupportView.IMAGE_VIEW:
                        view = new ThemeImageView(context, attrs);
                        break;
                    case DittoSupportView.BUTTON:
                        view = new ThemeButton(context, attrs);
                        break;
                    case DittoSupportView.FRAME_LAYOUT:
                        view = new ThemeFrameLayout(context, attrs);
                        break;
                    case DittoSupportView.RELATIVE_LAYOUT:
                        view = new ThemeRelativeLayout(context, attrs);
                        break;
                    case DittoSupportView.CONSTRAINT_LAYOUT:
                        view = new ThemeConstraintLayout(context, attrs);
                        break;
                    case DittoSupportView.LINEAR_LAYOUT:
                        view = new ThemeLinearLayout(context, attrs);
                        break;
                    case DittoSupportView.EDIT_TEXT:
                        view = new ThemeEditText(context, attrs);
                        break;
                    default:
                        view = delegate.createView(parent, name, context, attrs);
                }

                return view;
            }
        };
    }
}
