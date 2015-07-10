package com.example.de.taomi2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.de.taomi2.R;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_editliuyan extends Activity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_editliuyan);
        initLayout();
        initListennner();

    }
    private void initListennner(){
        findViewById(R.id.textView33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liuyan=editText.getText().toString().trim();
                if(liuyan.equals("")){
                    Toast.makeText(Activity_editliuyan.this,"留言不能为空",Toast.LENGTH_SHORT);
                }else{
                    Intent mintent=new Intent();
                    mintent.putExtra("liuyan",liuyan);
                    setResult(1,mintent);
                    finish();
                }
            }
        });
    }
    private void initLayout(){
        editText=(EditText)findViewById(R.id.editText3);
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("yunxingle");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){

        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
