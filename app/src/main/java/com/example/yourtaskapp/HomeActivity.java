package com.example.yourtaskapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
  Toolbar toolbar;
  FloatingActionButton fab_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar =findViewById(R.id.toolbar_home);
        fab_btn=findViewById(R.id.fab_btn);
        setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("Your Task App");
      fab_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              AlertDialog.Builder mydialog=new AlertDialog.Builder(HomeActivity.this);
              LayoutInflater inflater =LayoutInflater.from(HomeActivity.this);
              View view=inflater.inflate(R.layout.custominputfield,null);
              mydialog.setView(view);
              AlertDialog dialog=mydialog.create();
              final EditText title=view.findViewById(R.id.edt_title);
              final EditText note=view.findViewById(R.id.edt_note);
              Button btn_save=view.findViewById(R.id.btn_save);
              btn_save.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      String mtitle=title.getText().toString().trim();
                      String mnote=note.getText().toString().trim();
                      if (TextUtils.isEmpty(mtitle)){
                          title.setError("Required field");
                          return;
                      }
                      if (TextUtils.isEmpty(mnote)){
                          note.setError("Required field");
                          return;
                      }

                  }
              });
              mydialog.show();
          }
      });
    }
}
