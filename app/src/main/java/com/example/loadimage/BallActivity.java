package com.example.loadimage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.example.loadimage.custom.BallView;

/**
 * @author tangqipeng
 * @date 2020/9/4 3:02 PM
 * @email tangqipeng@aograph.com
 */
public class BallActivity extends AppCompatActivity {

    private ImageFilterView imageFilterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);
//        BallView ballView = new BallView(this);
//        imageFilterView = findViewById(R.id.imageFilterView);
//        imageFilterView.setImageDrawable(ballView);



    }


}
