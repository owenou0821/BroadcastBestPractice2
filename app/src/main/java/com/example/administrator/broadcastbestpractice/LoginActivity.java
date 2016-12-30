package com.example.administrator.broadcastbestpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016年12月29日 0029.
 */

public class LoginActivity extends BasicActivity {
    private  EditText account;
    private  EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login);

        Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = (EditText) findViewById(R.id.login_account_edit_text);
                password = (EditText) findViewById(R.id.lonin_password_edit_text);

                if (account.getText().toString().equals("ouwenlin")
                        && password.getText().toString().equals("365365")){
                    Toast.makeText(LoginActivity.this, "Login Succeed", Toast.LENGTH_LONG).show();
                    MainActivity.toMainActivity(LoginActivity.this);
                }else {
                    Toast.makeText(LoginActivity.this, "Account or Password mistake"+'\n'+"Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void toLoginActivity(Activity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
