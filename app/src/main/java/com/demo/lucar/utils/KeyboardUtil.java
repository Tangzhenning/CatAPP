package com.demo.lucar.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class KeyboardUtil {

    public static void show(Activity activity, View view) {
        show(activity, view, false);
    }

    public static void show(Activity activity, View view, boolean focus) {
        if (focus)
            view.requestFocus();
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hide(Activity activity, View view) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hide(Context context) {
        if (context != null) {
            InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            mInputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
        }
    }

    public interface OnSoftKeyboardChangeListener {
        void onSoftKeyBoardChange(int softKeyboardHeight, boolean visible);
    }

    public static boolean isShow(Activity activity) {
        Rect rect = new Rect();
        View decorView = activity.getWindow().getDecorView();
        int height = (int) (decorView.getHeight() * 0.7);
        decorView.getWindowVisibleDisplayFrame(rect);
        if (height > rect.bottom) {
            return true;
        } else {
            return false;
        }
    }
    public static ViewTreeObserver.OnGlobalLayoutListener observeSoftKeyboard(Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousKeyboardHeight = -1;
            Rect rect = new Rect();
            boolean lastVisibleState = false;

            @Override
            public void onGlobalLayout() {
                rect.setEmpty();
                decorView.getWindowVisibleDisplayFrame(rect);
                int displayHeight = rect.bottom - rect.top;
                int height = decorView.getHeight() - rect.top;
                int keyboardHeight = height - displayHeight;
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (double) displayHeight / height > 0.8;
                    if (hide != lastVisibleState) {
                        listener.onSoftKeyBoardChange(keyboardHeight, !hide);
                        lastVisibleState = hide;
                    }
                }
                previousKeyboardHeight = height;
            }
        };
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        return onGlobalLayoutListener;
    }

    public static ViewTreeObserver.OnGlobalLayoutListener observeSoftKeyboard(View view, final OnSoftKeyboardChangeListener listener) {
        final View decorView = view;
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousKeyboardHeight = -1;
            Rect rect = new Rect();
            boolean lastVisibleState = false;

            @Override
            public void onGlobalLayout() {
                rect.setEmpty();
                decorView.getWindowVisibleDisplayFrame(rect);
                int displayHeight = rect.bottom - rect.top;
                int height = decorView.getHeight() - rect.top;
                int keyboardHeight = height - displayHeight;
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (double) displayHeight / height > 0.8;
                    if (hide != lastVisibleState) {
                        listener.onSoftKeyBoardChange(keyboardHeight, !hide);
                        lastVisibleState = hide;
                    }
                }
                previousKeyboardHeight = height;
            }
        };
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        return onGlobalLayoutListener;
    }

    public static void removeSoftKeyboardObserver(Activity activity, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (listener == null) return;
        final View decorView = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            decorView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } else {
            decorView.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }

    public static void removeSoftKeyboardObserver(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (listener == null) return;
        final View decorView = view;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            decorView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } else {
            decorView.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }


    public static void noEnter(EditText editText){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                    return true;
                return false;
            }
        });
    }
}