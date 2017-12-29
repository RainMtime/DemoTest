package com.example.chunyu.demotest.customeView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.chunyu.demotest.Utils.DemoUtils;

/**
 * Created by chunyu on 2017/12/26.
 */

public class RadioSwipeCardView extends RelativeLayout {

    private float TouchX;
    private float TouchY;
    private float sumDistanceX;
    private float sumDistanceY;
    private float lastX;
    private float lastY;

    private float mTopCardViewX;
    private float mTopCardViewY;


    private boolean hasFloatingView;

    private ImageView floatingView;
    private View mBottomCardView;
    private View mTopCardView;
    private ViewGroup decorView;

    private boolean mNeedIntercept = false;

    public RadioSwipeCardView(Context context) {
        super(context);
        init(context);
    }

    public RadioSwipeCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RadioSwipeCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setClickable(true);
        createChildView();
        setFocusable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        mNeedIntercept = isDispatchTouchEvent(event);
        Log.i("chunyu-actionmask", "mask:" + event.getActionMasked());
        return super.dispatchTouchEvent(event);
    }


    private boolean isDispatchTouchEvent(MotionEvent event) {
        boolean comsumed = false;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                Log.i("chunyu-down", "intercept-aciton_down");
                if (decorView == null) {
                    if (getContext() instanceof Activity) {
                        decorView = getDecorView(getContext());
                    }
                }
                if (mTopCardView == null) {
                    mTopCardView = retriveTopCardView();
                    int[] xy = new int[2];
                    mTopCardView.getLocationOnScreen(xy);
                    mTopCardViewX = xy[0];
                    mTopCardViewY = xy[1];
                }
                TouchX = event.getRawX();
                TouchY = event.getRawY();
                lastX = TouchX;
                lastY = TouchY;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("chunyu-cancel", "intercept-aciton_cancel");
                if (hasFloatingView) {
                    comsumed = true;
                }
                resetView();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("chunyu-move", "intercept-aciton_move");
                getParent().requestDisallowInterceptTouchEvent(true);
                sumDistanceX = event.getRawX() - TouchX;
                sumDistanceY = event.getRawY() - TouchY;

                float dealtX = event.getRawX() - lastX;
                float dealtY = event.getRawY() - lastY;
                if (!hasFloatingView) {
                    mTopCardView = retriveTopCardView();
                    addViewToDecorView(mTopCardView);
                }

                if (hasFloatingView) {
                    translateView(dealtX, dealtY, sumDistanceX, sumDistanceY);
                    comsumed = true;
                }

                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                Log.i("chunyu-up", "intercept-aciton_up");
                if (hasFloatingView) {
                    comsumed = true;
                }
                resetView();
                break;
            default:
                break;
        }

        return comsumed;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mNeedIntercept;
    }


    private void createChildView() {
        mBottomCardView = createBottomCardView();
        mTopCardView = createTopCardView();
        addView(mBottomCardView);
        addView(mTopCardView);
        mBottomCardView.setRotation(-5);
    }

    protected View createTopCardView() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT, 300);
        params2.setMargins(50, 10, 50, 50);
        imageView.setLayoutParams(params2);
        imageView.setBackground(new ColorDrawable(DemoUtils.getRandomColor()));
        return imageView;
    }

    protected View createBottomCardView() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 300);
        params.setMargins(50, 10, 50, 50);
        imageView.setLayoutParams(params);
        imageView.setBackground(new ColorDrawable(DemoUtils.getRandomColor()));
        return imageView;
    }

    protected void renderTopCardView() {

    }

    protected void renderBottomCardView() {

    }


    public void translateView(float x, float y, float sumX, float sumY) {
        Log.i("chunyu-translateView", "x:" + x + "\t y:" + y + "\tsumX:" + sumX + "\tsumY:" + sumY);
        if (floatingView != null && hasFloatingView) {
            float translationX = floatingView.getTranslationX();
            float translationY = floatingView.getTranslationY();
            float widthRule = floatingView.getWidth() / 2;
            float heightRule = floatingView.getHeight();


            floatingView.setTranslationX(translationX + x);
            floatingView.setTranslationY(translationY + y);
            floatingView.setRotation(10 * (sumX / widthRule));
            floatingView.setAlpha(1.0f - 0.6f * Math.abs(sumY / heightRule));

        }
    }


    private void resetView() {
        if (decorView != null && floatingView != null) {
            AnimatorSet animationSet = new AnimatorSet();

            animationSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.i("chunyu", "onAnimationStart");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    removeFloatingView();
                    Log.i("chunyu", "onAnimationEnd");
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    removeFloatingView();

                    Log.i(" chunyu", "onAinmationCancel");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            if (Math.abs(sumDistanceX) < 100 && Math.abs(sumDistanceY) < 100) {
                animationSet.playTogether(
                        ObjectAnimator.ofFloat(floatingView, "rotation", floatingView.getRotation(), 0),
                        ObjectAnimator.ofFloat(floatingView, "translationX", floatingView.getTranslationX(), mTopCardViewX),
                        ObjectAnimator.ofFloat(floatingView, "translationY", floatingView.getTranslationY(), mTopCardViewY),
                        ObjectAnimator.ofFloat(floatingView, "alpha", floatingView.getAlpha(), 1.0f));
                animationSet.setDuration(200).start();
            } else {
                animationSet.playTogether(
                        ObjectAnimator.ofFloat(floatingView, "rotation", floatingView.getRotation(), floatingView.getRotation() + (float) (10 * getcosValue(sumDistanceX, sumDistanceY))),
                        ObjectAnimator.ofFloat(floatingView, "translationX", floatingView.getTranslationX(), floatingView.getTranslationX() + 200.0f * (float) getcosValue(sumDistanceX, sumDistanceY)),
                        ObjectAnimator.ofFloat(floatingView, "translationY", floatingView.getTranslationY(), floatingView.getTranslationY() + 200.0f * (float) getsinValue(sumDistanceX, sumDistanceY)),
                        ObjectAnimator.ofFloat(floatingView, "alpha", floatingView.getAlpha(), 0.0f)
                );

                animationSet.setDuration(300).start();
            }


        } else {
            Log.e("chunyu-test", "decorView == null,or ");
        }

        TouchX = -1;
        TouchY = -1;
        sumDistanceY = -1;
        sumDistanceX = -1;
        lastX = -1;
        lastY = -1;
    }

    private double getcosValue(float x, float y) {
        return x / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    private double getsinValue(float x, float y) {
        return y / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }


    private void removeFloatingView() {
        if (hasFloatingView && decorView != null) {
            decorView.removeView(floatingView);
            hasFloatingView = false;
        }
    }

    @Nullable
    public View retriveTopCardView() {
        int childCount = getChildCount();
        if (childCount >= 1) {
            return getChildAt(childCount - 1);
        }
        return null;
    }

    @Nullable
    public Bitmap createViewDrawable(View view) {
        view.setDrawingCacheEnabled(true);
        return Bitmap.createBitmap(view.getDrawingCache());
    }


    public void addViewToDecorView(View view) {

        if (decorView != null && !hasFloatingView) {
            ViewGroup viewGroup = getDecorView((Activity) getContext());
            floatingView = new ImageView(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            floatingView.setLayoutParams(layoutParams);
            floatingView.setImageBitmap(createViewDrawable(view));
            viewGroup.addView(floatingView);

            int xy[] = new int[2];
            view.getLocationOnScreen(xy);
            floatingView.setTranslationY(xy[1]);
            floatingView.setTranslationX(xy[0]);
            hasFloatingView = true;
        }
    }

    @NonNull
    private ViewGroup getDecorView(Context context) {

        if (context instanceof Activity) {
            return (ViewGroup) ((Activity) context).getWindow().getDecorView();
        } else {
            throw new IllegalStateException("context is not Activity context!");
        }
    }

}
