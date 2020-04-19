package com.example.ditto.event;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.ditto.widget.view.IThemeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

/**
 * 与当前的Activity绑定的Consumer, 处理收到的DittoEvent, 进行换肤通知
 *
 * @author menghaonan
 * @date 2020/04/19
 */
public class DittoEventConsumer implements Consumer<DittoEvent>, LifecycleObserver {

    private WeakReference<Activity> mActivity;

    public DittoEventConsumer(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void accept(DittoEvent event) {
        if (mActivity.get() == null) {
            return;
        }
        Window window = mActivity.get().getWindow();
        if (window == null) {
            return;
        }
        notifyThemeViews(window.getDecorView(), event);
    }

    /**
     * 遍历当前Activity所有View, 通知ThemeView换肤
     */
    private void notifyThemeViews(View view, DittoEvent event) {
        if (view instanceof IThemeView) {
            ((IThemeView) view).switchTheme(event);
        }
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            for (int i = 0; i < parent.getChildCount(); i++) {
                notifyThemeViews(parent.getChildAt(i), event);
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        mActivity.clear();
        mActivity = null;
    }
}
