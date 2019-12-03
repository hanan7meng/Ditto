package com.example.switchtheme;

import android.app.Activity;
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

    private AppCompatDelegate mDelegate;

    private volatile static ThemeViewInflater sInstance;

    private ThemeViewInflater(Activity activity) {
        mDelegate = AppCompatDelegate.create(activity, null);
    }

    public static ThemeViewInflater getInstance(Activity activity) {
        if (sInstance == null) {
            synchronized (ThemeViewInflater.class) {
                if (sInstance == null) {
                    sInstance = new ThemeViewInflater(activity);
                }
            }
        }
        return sInstance;
    }


    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return mDelegate.createView(null, name, context, attrs);
            }

            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                View view = null;
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
                }

                if (view == null) {
                    return mDelegate.createView(parent, name, context, attrs);
                }

                return view;
            }
        };
    }
}
