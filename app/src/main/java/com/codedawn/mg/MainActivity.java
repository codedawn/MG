package com.codedawn.mg;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.codedawn.mg.db.DBOpenHelper;
import com.codedawn.mg.view.HomeActivity;
import com.codedawn.mg.view.RegisterActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUser;
    private EditText etPassword;
    private CheckBox cbPWD;
    private Button btRegister;
    private Button btLogin;
    private String SP_USER="sp_user";
    private String SP_PASSWORD = "sp_password";
    private String SP_IS_REMEMBER_PSD = "sp_is_remember_psd";
    private SharedPreferences sharedPreferences;
    private static boolean mIsChecked = false;
    private String TAG = "MainActivity";
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues cv;
    private static String userName;
    private static String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        init();
        initData();



    }

    private void initData() {


        if (sharedPreferences == null){
            //实例化一个SharedPreference对象
            sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);

        }

        //回显数据
        etUser.setText(sharedPreferences.getString(SP_USER,""));
        etPassword.setText(sharedPreferences.getString(SP_PASSWORD,""));
        mIsChecked = sharedPreferences.getBoolean(SP_IS_REMEMBER_PSD,false);
        cbPWD.setChecked(mIsChecked);


    }

    private void init() {
        //获取用户名和密码输入框
        etUser=findViewById(R.id.et_user);
        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mIsChecked){

                    if(sharedPreferences==null){
                        //实例化一个SharedPreference对象
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }
                    //实例化一个SharedPreference的编辑者对象
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SP_USER,etUser.getText().toString());
                    //提交
                    edit.commit();
                }


            }
        });
        etPassword=findViewById(R.id.et_password);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mIsChecked) {
                    //记录密码变化情况
                    if (sharedPreferences == null) {
                        //实例化一个SharedPreference对象
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }
                    //实例化一个SharedPreference的编辑者对象
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SP_PASSWORD, etPassword.getText().toString());
                    //提交
                    edit.commit();
                }
            }
        });
        //获取勾选按钮
        cbPWD=findViewById(R.id.cb_psd);

        cbPWD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mIsChecked = isChecked;

                if(isChecked){
                    if(sharedPreferences==null){
                        //实例化一个SharedPreference对象
                        sharedPreferences = getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                    }

                    //实例化一个SharedPreference的编辑者对象
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    //存储数据
                    edit.putString(SP_USER,etUser.getText().toString());
                    edit.putString(SP_PASSWORD,etPassword.getText().toString());
                    edit.putBoolean(SP_IS_REMEMBER_PSD,mIsChecked);
                    //提交
                    edit.commit();
                }
            }
        });
        findViewById(R.id.bt_register).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.bt_login).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.bt_register:
                //跳转到注册界面

                intent.setClass(getApplicationContext(), RegisterActivity.class);
                this.startActivity(intent);
                break;
            case R.id.bt_login:

                userName=etUser.getText().toString();
                password=etPassword.getText().toString();
                if(login(userName,password)){
                    Toast.makeText(MainActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
                    intent.setClass(getApplicationContext(), HomeActivity.class);
                    this.startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    private boolean login(String userName, String password) {
        dbOpenHelper = new DBOpenHelper(this,"user.db",null,1);
        db = dbOpenHelper.getWritableDatabase();
        String sql = "select * from user where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {userName, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;

    }
}
