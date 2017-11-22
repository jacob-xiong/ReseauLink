package com.reseau.link.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.reseau.link.utils.Utils;

import com.reseau.link.R;

public class LoginActivity extends AppCompatActivity {
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Utils.getIntent(LoginActivity.this, R.string.test);
                startActivity(intent);
            }
        });
    }
}
