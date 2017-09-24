package com.dev.brian.sharedpreferencesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.brian.sharedpreferencesdemo.util.SharedPrefsUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    EditText edit_name;
    Button btn_login;
    String name;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        // 完成sp的初始化。
        sp = getSharedPreferences("config", MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("save_name", true);//从sp中取出状态，判断是否已登录
        if (isLogin) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("save_name", false);
            editor.commit();
            Log.d("test", "是第一次開啟");
        } else {
            Log.d("test", "不是第一次開啟");
            Intent intent = new Intent(this, SuccessActivity.class);
            name = sp.getString("pass", "");
            intent.putExtra("name", name);
            startActivity(intent);
        }
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
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("pass", name);
                    editor.commit();

                    Intent intent = new Intent(this, SuccessActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
                break;
        }
    }
}
