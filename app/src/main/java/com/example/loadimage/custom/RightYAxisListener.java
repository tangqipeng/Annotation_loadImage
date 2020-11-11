package com.example.loadimage.custom;

import android.graphics.Canvas;

import androidx.annotation.ColorRes;

import com.example.loadimage.exception.DiagramException;

/**
 * @author tangqipeng
 * @date 2020/11/10 6:36 PM
 * @email tangqipeng@aograph.com
 */
public interface RightYAxisListener {

    boolean openRightYAxle();

    int getRightYAxleBaseCellNum();

    int getRightYAxleBaseCell();

    String getRightYAxleBaseCellText(int position);

    int getRightYAxleSmallestCell() throws DiagramException;

    int getRightYAxleBaseCellSegmentationNum();

    float getRightCellValue(int type, int position);

    @ColorRes
    int getRightItemColor(int type);

    void drawCell(Canvas canvas);

}
