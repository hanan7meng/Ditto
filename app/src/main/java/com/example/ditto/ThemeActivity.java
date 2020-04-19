package com.example.ditto;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;

import com.example.ditto.event.DittoEventConsumer;
import com.example.ditto.event.handler.DefaultDittoEventHandler;
import com.example.ditto.event.handler.IDittoEventHandler;
import com.example.ditto.widget.DittoViewInflater;

/**
 * @author menghaonan
 * @date 2020/04/19
 */
public class ThemeActivity extends AppCompatActivity {

    private IDittoEventHandler mDittoEventHandler;
    private DittoEventConsumer mDittoEventConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initDitto();
        super.onCreate(savedInstanceState);
    }

    private void initDitto() {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this),
                DittoViewInflater.getInstance().getLayoutInflaterFactory(AppCompatDelegate.create(this, null)));

        mDittoEventHandler = new DefaultDittoEventHandler();
        mDittoEventConsumer = new DittoEventConsumer(this);
        getLifecycle().addObserver(mDittoEventConsumer);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mDittoEventHandler.register(mDittoEventConsumer);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mDittoEventHandler.unRegister(mDittoEventConsumer);
    }
}
