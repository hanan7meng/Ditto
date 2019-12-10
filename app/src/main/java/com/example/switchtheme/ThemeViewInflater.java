package com.example.switchtheme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.switchtheme.view.ThemeButton;
import com.example.switchtheme.view.ThemeConstraintLayout;
import com.example.switchtheme.view.ThemeFrameLayout;
import com.example.switchtheme.view.ThemeImageView;
import com.example.switchtheme.view.ThemeLinearLayout;
import com.example.switchtheme.view.ThemeRelativeLayout;
import com.example.switchtheme.view.ThemeTextView;

import com.example.switchtheme.data.ThemeConst.ThemeSupportView;
import com.example.switchtheme.view.ThemeView;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class ThemeViewInflater {

    private volatile static ThemeViewInflater sInstance;

    private static final String NAME_SPACE = "http://schemas.android.com/apk/res-auto";

    private ThemeViewInflater() {}

    public static ThemeViewInflater getInstance() {
        if (sInstance == null) {
            synchronized (ThemeViewInflater.class) {
                if (sInstance == null) {
                    sInstance = new ThemeViewInflater();
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
                boolean enable = attrs.getAttributeBooleanValue(NAME_SPACE, "theme_enable", false);
                if (!enable) {
                    return delegate.createView(parent, name, context, attrs);
                }

                View view;
                switch (name) {
                    case ThemeSupportView.VIEW:
                        view = new ThemeView(context, attrs);
                        break;
                    case ThemeSupportView.TEXT_VIEW:
                        view = new ThemeTextView(context, attrs);
                        break;
                    case ThemeSupportView.IMAGE_VIEW:
                        view = new ThemeImageView(context, attrs);
                        break;
                    case ThemeSupportView.BUTTON:
                        view = new ThemeButton(context, attrs);
                        break;
                    case ThemeSupportView.FRAME_LAYOUT:
                        view = new ThemeFrameLayout(context, attrs);
                        break;
                    case ThemeSupportView.RELATIVE_LAYOUT:
                        view = new ThemeRelativeLayout(context, attrs);
                        break;
                    case ThemeSupportView.CONSTRAINT_LAYOUT:
                        view = new ThemeConstraintLayout(context, attrs);
                        break;
                    case ThemeSupportView.LINEAR_LAYOUT:
                        view = new ThemeLinearLayout(context, attrs);
                        break;
                    default:
                        view = delegate.createView(parent, name, context, attrs);
                }

                return view;
            }
        };
    }
}
