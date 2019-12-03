package com.example.switchtheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.switchtheme.data.ThemeConst;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class SecondaryActivity extends AppCompatActivity {

    private Button btnDefault;
    private Button btnDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), ThemeViewInflater.getInstance(this).getLayoutInflaterFactory());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);


        btnDefault = findViewById(R.id.btn_default);
        btnDefault.setOnClickListener(v -> ThemeManager.getInstance().switchTheme(ThemeConst.Theme.THEME_DEFAULT));

        btnDark = findViewById(R.id.btn_dark);
        btnDark.setOnClickListener(v -> ThemeManager.getInstance().switchTheme(ThemeConst.Theme.THEME_DARK));
    }
}
