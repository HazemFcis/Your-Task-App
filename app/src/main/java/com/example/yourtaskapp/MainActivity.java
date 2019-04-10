package com.example.yourtaskapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
  TextView signup;
  EditText email;
  EditText pass;
  Button bt_login;
  FirebaseAuth firebaseAuth;
  ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=findViewById(R.id.tv_signup);
        email=findViewById(R.id.ed_login_email);
        pass=findViewById(R.id.ed_login_Password);
        bt_login=findViewById(R.id.bt_login);
        firebaseAuth=FirebaseAuth.getInstance();
        mProgressDialog=new ProgressDialog(this);
       /** if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }**/
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail=email.getText().toString().trim();
                String mpass=pass.getText().toString().trim();
                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required field");
                    return;
                }
                if (TextUtils.isEmpty(mpass)){
                    email.setError("Required field");
                    return;
                }
                mProgressDialog.setMessage("processing ..");
                mProgressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(mEmail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Sucessful",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            mProgressDialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(),"Problem",Toast.LENGTH_LONG).show();
                            mProgressDialog.dismiss();
                        }
                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });
    }
}
