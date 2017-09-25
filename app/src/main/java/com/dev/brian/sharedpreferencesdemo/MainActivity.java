package com.dev.brian.sharedpreferencesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.brian.sharedpreferencesdemo.utils.PreferencesUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_name;
    Button btn_login;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // 初始化
        initView();
        boolean isLogin = PreferencesUtils.getBoolean(this, "save_name", true);
        if (isLogin)
            return;

        name = PreferencesUtils.getString(this, "pass", "");
        PreferencesUtils.putBoolean(this, "save_name", false);
        // 获取文本框内容
        Intent intent = new Intent(this, SuccessActivity.class);
        // 传递文本框内容
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }

    private void initView() {
        // 文本输入框 密码
        edit_name = (EditText) findViewById(R.id.edit_name);
        // 登录按钮
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                name = edit_name.getText().toString();
                // 验证
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    PreferencesUtils.putBoolean(this, "save_name", false);
                    // 存储密码
                    PreferencesUtils.putString(this, "pass", name);
                    // 成功
                    Intent intent = new Intent(this, SuccessActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
