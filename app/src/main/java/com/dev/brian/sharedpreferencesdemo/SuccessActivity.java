package com.dev.brian.sharedpreferencesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dev.brian.sharedpreferencesdemo.utils.PreferencesUtils;

public class SuccessActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txt_show;
    Button btn_cancel;
    Button btn_1, btn_2, btn_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_success);
        initView();
        // 获取传递过来的数据
        String name = getIntent().getStringExtra("name");
        txt_show.setText(name);
        // 权限判断
        if (name.equals("admin")) {
            btn_1.setVisibility(View.GONE);
        }
    }

    private void initView() {
        txt_show = (TextView) findViewById(R.id.txt_show);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 注销
            case R.id.btn_cancel:
                // 修改存储的账号
                PreferencesUtils.putBoolean(this, "save_name", true);
                PreferencesUtils.putString(this, "pass", null);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
