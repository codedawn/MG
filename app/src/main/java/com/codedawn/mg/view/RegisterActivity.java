package com.codedawn.mg.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.codedawn.mg.MainActivity;
import com.codedawn.mg.R;
import com.codedawn.mg.db.DBOpenHelper;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RegisterActivity";
    private EditText etUser;
    private EditText etPassword;
    private Button btRegister;
    private ImageButton ibReturn;
    private SQLiteDatabase db;
    private DBOpenHelper dbOpenHelper ;
    private Cursor cursor;
    private static String userName;
    private static String password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser=findViewById(R.id.et_registerUser);
        etPassword=findViewById(R.id.et_registerPSD);
        findViewById(R.id.bt_register).setOnClickListener(this);
        findViewById(R.id.ib_return).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();


        switch (v.getId()){
            case R.id.ib_return:

                intent.setClass(getApplicationContext(), MainActivity.class);
                this.startActivity(intent);
                break;
            case R.id.bt_register:
                userName = etUser.getText().toString();
                password = etPassword.getText().toString();


                if(userName.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"用户名或密码不能为空" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if(userName.length()>15||userName.length()<5){
                    Toast.makeText(getApplicationContext(),"用户名长度应在5-15字符" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6 || password.length()>18){
                    Toast.makeText(getApplicationContext(),"密码长度应在6-18字符" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if(CheckIsUserNameAlreadyInDB(userName)){
                    Toast.makeText(getApplicationContext(),"该用户名已存在，请重新注册" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(register(userName,password)){
                        System.out.println("注册成功");
                        Toast.makeText(getApplicationContext(),"注册成功" ,Toast.LENGTH_SHORT).show();
                        intent.setClass(getApplicationContext(),MainActivity.class);
                    }
                }

                break;
        }




    }
    private boolean CheckIsUserNameAlreadyInDB(String userName) {
        dbOpenHelper = new DBOpenHelper(this,"user.db",null,1);
        db = dbOpenHelper.getReadableDatabase();
        String Query = "select * from user where name =?";
        cursor = db.rawQuery(Query,new String[] { userName });
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;
    }

    private boolean register(String userName, String password) {
        dbOpenHelper = new DBOpenHelper(this,"user.db",null,1);
        db = dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",userName);
        values.put("password",password);
        db.insert("user",null,values);
        db.close();
        return true;
    }


}
