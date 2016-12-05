package com.xyz.keyboarddemo;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;


/**
 * 作者：xy_z on 2016/3/18 13:05
 * 邮箱：xyz@163.com
 */
public class KeyboardUtil {
    private KeyboardView keyboardView;   //keyBoardView组件
    private Keyboard k1;// 数字键盘
    private EditText ed;
    private int KEY_ENTER_CODE = 13;

    public KeyboardUtil(Activity act, Context ctx, EditText edit) {
        this.ed = edit;
        k1 = new Keyboard(ctx, R.xml.number);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(listener);
    }


    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
            // TODO Auto-generated method stub
        }


        @Override
        public void swipeRight() {
            // TODO Auto-generated method stub
        }


        @Override
        public void swipeLeft() {
            // TODO Auto-generated method stub
        }


        @Override
        public void swipeDown() {
            // TODO Auto-generated method stub
        }


        @Override
        public void onText(CharSequence text) {
            // TODO Auto-generated method stub
        }


        @Override
        public void onRelease(int primaryCode) {
            if (KEY_ENTER_CODE == primaryCode) {
                if (null != onEnterListener) {
                    onEnterListener.enter();
                }
                hideKeyboard();
            }
        }


        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            int start = ed.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_CANCEL) // 完成
            {
                hideKeyboard();
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == 57419) { // go left
                if (start > 0) {
                    ed.setSelection(start - 1);
                }
            } else if (primaryCode == 57421) { // go right
                if (start < ed.length()) {
                    ed.setSelection(start + 1);
                }
            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    };


    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

    public void setOnEnterListener(EnterListener onEnterListener) {
        this.onEnterListener = onEnterListener;
    }

    private EnterListener onEnterListener;

    interface EnterListener {
        void enter();
    }
}
