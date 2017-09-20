package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by chunyu on 2017/9/4.
 */

public class AccessibilityTextView extends View {
    public AccessibilityTextView(Context context) {
        super(context);
    }

    @Override
    public void announceForAccessibility(CharSequence text) {
        super.announceForAccessibility(text);
    }

    public AccessibilityTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccessibilityTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);

        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
            event.getText().add("hello,xiaoyu");
        }
    }
}
