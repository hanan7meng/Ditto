package com.example.switchtheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.switchtheme.view.ThemeTextView;
import com.example.switchtheme.view.ThemeView;

import com.example.switchtheme.data.ThemeConst.Theme;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), ThemeViewInflater.getInstance(this).getLayoutInflaterFactory());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.theme_layout);
        // 动态方式
        final ThemeView view = new ThemeView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100, 100);
        params.gravity = Gravity.CENTER;
        view.setThemeBackground(R.color.view_bg_color);
        frameLayout.addView(view, 0, params);

        ThemeTextView mTv = new ThemeTextView(this);
        mTv.setText("hello world");
        mTv.setThemeTextColor(R.color.text_color);
        mTv.setThemeBackground(R.color.view_bg_color);
        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.topMargin = 200;
        frameLayout.addView(mTv, params);

        Button btnDefault = findViewById(R.id.btn_default);
        btnDefault.setOnClickListener(v -> ThemeManager.getInstance().switchTheme(Theme.THEME_DEFAULT));

        Button btnDark = findViewById(R.id.btn_dark);
        btnDark.setOnClickListener(v -> ThemeManager.getInstance().switchTheme(Theme.THEME_DARK));

        Button btnJump = findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
            startActivity(intent);
        });
    }
}
