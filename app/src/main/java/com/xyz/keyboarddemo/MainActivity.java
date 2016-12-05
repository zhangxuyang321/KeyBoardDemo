package com.xyz.keyboarddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText recharge_money_et;
    private KeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recharge_money_et = (EditText) findViewById(R.id.recharge_money_et);
        keyboardUtil = new KeyboardUtil(MainActivity.this, MainActivity.this, recharge_money_et);
        init();
    }

    public void init() {
        recharge_money_et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int numberType = recharge_money_et.getInputType();
                recharge_money_et.setInputType(InputType.TYPE_NULL);
                keyboardUtil.showKeyboard();
                recharge_money_et.setInputType(numberType);
                return true;
            }
        });
        keyboardUtil.setOnEnterListener(new KeyboardUtil.EnterListener() {
            @Override
            public void enter() {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
