package com.lanshifu.launchtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import androidx.multidex.MultiDex;

import java.io.File;

public class LoadMultiDexActivity extends Activity {
    private static final String TAG = "lxb-LoadMultiDexActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_multi_dex);


        Thread thread = new Thread() {
            @Override
            public void run() {

                loadMultiDex();
            }
        };
        thread.setName("multi_dex");
        thread.start();

        showLoadingDialog();
    }


    private void loadMultiDex(){
        Log.d(TAG, "MultiDex.install 开始: ");
        long startTime = System.currentTimeMillis();
        MultiDex.install(LoadMultiDexActivity.this);
        Log.d(TAG, "MultiDex.install 结束，耗时: " + (System.currentTimeMillis() - startTime));

        try {
            //模拟MultiDex耗时很久的情况
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        aftetMultiDex();
    }

    private void aftetMultiDex() {

        deleteTempFile(this);

        //将这个进程杀死
        Log.d(TAG, "aftetMultiDex: ");
        finish();
        Process.killProcess(Process.myPid());
    }

    private void deleteTempFile(Context context) {
        try {
            File file = new File(context.getCacheDir().getAbsolutePath(), "load_dex.tmp");
            if (file.exists()) {
                file.delete();
                Log.d(TAG, "deleteTempFile: ");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void showLoadingDialog(){
        new AlertDialog.Builder(this)
                .setMessage("加载中，请稍后...")
                .show();
    }

}
