package com.example.loadimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.example.annotationlib.Check;
import com.example.loadimage.mode.Circle;
import com.example.loadimage.mode.IShape;
import com.example.loadimage.glide.MyGlide;
import com.example.loadimage.mode.decorator.RedShapeDecorator;
import com.example.loadimage.mode.factory.ShapeFactory;

/**
 * @author tangqipeng
 * @date 2020/9/3 9:51 AM
 * @email tangqipeng@aograph.com
 */
public class SplashActivity extends AppCompatActivity {

    private ImageFilterView filterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        filterView = findViewById(R.id.filterView);
        MyGlide.with(this).loading(R.mipmap.meinv_3).load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2550253929,3330095031&fm=26&gp=0.jpg").into(filterView);

        filterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //装饰模式
        IShape redCircle = new RedShapeDecorator(new Circle());
        redCircle.draw();

        //工厂模式
        ShapeFactory factory = new ShapeFactory();
        IShape shape = factory.create("Rectangle");
        shape.draw();

        //字节码插桩
        initView();
    }

    @Check
    private void initView(){

    }
}
