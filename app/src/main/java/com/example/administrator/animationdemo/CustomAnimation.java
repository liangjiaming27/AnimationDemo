package com.example.administrator.animationdemo;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

public class CustomAnimation extends Animation {

    private Camera camera;
    private int mCenterWidth;
    private int mCenterHeigth;

    public CustomAnimation() {
        camera = new Camera();
    }

    //第一参数是插值器时间因子 , 第二个参数是矩阵的封装类
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        matrix.preScale(1,1-interpolatedTime,mCenterWidth,mCenterHeigth);
        camera.save();
        camera.rotateY(45*interpolatedTime);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-mCenterWidth, -mCenterHeigth);
        matrix.postTranslate(mCenterWidth, mCenterHeigth);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(1000);
        setFillAfter(true);//动画结束后保留状态
        setInterpolator(new BounceInterpolator());
        mCenterWidth = width/2;
        mCenterHeigth = height/2;
    }
}
