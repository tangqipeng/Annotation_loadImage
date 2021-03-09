package com.example.loadimage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loadimage.custom.MyLayout;

/**
 * @author tangqipeng
 * @date 12/18/20 11:45 AM
 * @email tangqipeng@aograph.com
 */
public class TestMyActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylayout);
        MyLayout mylayout = findViewById(R.id.mylayout);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);

        mylayout.setOnTouchListener((v, event) -> {
            Log.d("TAG", "myLayout on touch");
            return false;
        });

        btn1.setOnTouchListener((v, event) -> {
            Log.d("TAG", "You touch button1");
            return false;
        });

//        btn1.setOnClickListener(v -> Log.d("TAG", "You clicked button1"));

        btn2.setOnTouchListener((v, event) -> {
            Log.d("TAG", "You touch button2");
            return false;
        });

        btn2.setOnClickListener(v -> Log.d("TAG", "You clicked button2"));

    }
}
