package com.example.ditto;

import android.os.Bundle;
import android.widget.Button;

import com.example.ditto.model.DittoConst;
import com.example.ditto.widget.view.ThemeTextView;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class MainActivity extends ThemeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lightBtn = findViewById(R.id.btn_light);
        Button darkBtn = findViewById(R.id.btn_dark);
        Button cancelBtn = findViewById(R.id.btn_cancel);

        ThemeTextView tv = findViewById(R.id.tv_test);
        tv.setThemeTextColor(R.color.text_color);


        lightBtn.setOnClickListener(v -> DittoManager.getInstance().switchTheme(DittoConst.Theme.THEME_DEFAULT));
        darkBtn.setOnClickListener(v -> DittoManager.getInstance().switchTheme(DittoConst.Theme.THEME_DARK));
        cancelBtn.setOnClickListener(v -> tv.removeThemeTextColor());
    }
}
