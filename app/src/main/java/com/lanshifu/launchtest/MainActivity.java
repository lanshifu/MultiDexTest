package com.lanshifu.launchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zhangyue.we.x2c.X2C;
import com.zhangyue.we.x2c.ano.Xml;


/**
 * UI 优化，xml 编译期转 java代码
 */
@Xml(layouts = "activity_main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        setTitle("主页");
        X2C.setContentView(this, R.layout.activity_main);

    }
}
