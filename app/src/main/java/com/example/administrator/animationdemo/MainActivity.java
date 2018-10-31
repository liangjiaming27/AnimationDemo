package com.example.administrator.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvAlpha;
    private TextView tvRotate;
    private TextView tvTranslate;
    private TextView tvScale;
    private TextView tvSet;
    private TextView tvObj;
    private TextView tvWrapper;
    private TextView tvObjProperty;
    private TextView tvValue;
    private TextView tvColor;
    private TextView tvXml;
    private TextView tvCustom;
    private TextView tvLoad;
    private TextView tvObjPropertyKey;
    private ImageView imageViewSVG;
    private AlphaAnimation alphaAnimation;
    private RotateAnimation rotateAnimation;
    private TranslateAnimation translateAnimation;
    private ScaleAnimation scaleAnimation;
    private AnimationSet animationSet;
    private ObjectAnimator objectAnimator;
    private ObjectAnimator objectAnimatorWrapper;
    private ObjectAnimator objectAnimatorProper;
    private PropertyValuesHolder valuesHolderTranslastion;
    private PropertyValuesHolder valuesHolderScaleX;
    private PropertyValuesHolder valuesHolderScaleY;
    private PropertyValuesHolder valuesHolderColor;
    private static final String TAG = "MainActivity";
    private WrapperView wrapperView;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAlpha = findViewById(R.id.tv_alpha);
        tvRotate = findViewById(R.id.tv_rotate);
        tvXml = findViewById(R.id.tv_xml);
        tvTranslate = findViewById(R.id.tv_translate);
        tvScale = findViewById(R.id.tv_scale);
        tvSet = findViewById(R.id.tv_set);
        tvObj = findViewById(R.id.tv_obj);
        tvWrapper = findViewById(R.id.tv_obj_wrapper);
        tvObjProperty = findViewById(R.id.tv_obj_property);
        tvObjPropertyKey = findViewById(R.id.tv_obj_property_key);
        tvValue = findViewById(R.id.tv_value);
        tvColor = findViewById(R.id.tv_color);
        tvLoad = findViewById(R.id.tv_load);
        tvCustom = findViewById(R.id.tv_custom);
        imageViewSVG = findViewById(R.id.image_svg);

        tvAlpha.setOnClickListener(this);
        tvColor.setOnClickListener(this);
        tvRotate.setOnClickListener(this);
        tvTranslate.setOnClickListener(this);
        tvScale.setOnClickListener(this);
        tvSet.setOnClickListener(this);
        tvObj.setOnClickListener(this);
        tvWrapper.setOnClickListener(this);
        tvObjProperty.setOnClickListener(this);
        tvObjPropertyKey.setOnClickListener(this);
        tvValue.setOnClickListener(this);
        tvXml.setOnClickListener(this);
        tvLoad.setOnClickListener(this);
        tvCustom.setOnClickListener(this);
        imageViewSVG.setOnClickListener(this);

        alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);

        rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        rotateAnimation.setDuration(1000);

        translateAnimation = new TranslateAnimation(0, 200, 0, 0);
        translateAnimation.setDuration(1000);

        scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);

        animationSet = new AnimationSet(true);
        animationSet.setDuration(4000);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e(TAG, "onAnimationStart: 动画开始执行的时候调用");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e(TAG, "onAnimationEnd: 动画结束执行的时候调用");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.e(TAG, "onAnimationRepeat: 动画重复执行的时候调用");
            }
        });

        objectAnimator = ObjectAnimator.ofFloat(tvObj, "translationX", 200);
        objectAnimator.setDuration(1000);

        wrapperView = new WrapperView(tvWrapper);
        objectAnimatorWrapper = ObjectAnimator.ofInt(wrapperView, "width", 100).setDuration(1000);
        objectAnimatorWrapper.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG, "onAnimationStart: 动画开始执行的时候调用");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //这个应该是用得最多的吧
                Log.e(TAG, "onAnimationEnd: 动画结束执行的时候调用");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG, "onAnimationEnd: 动画取消执行的时候调用");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "onAnimationEnd: 动画重复执行的时候调用");
            }
        });

        valuesHolderTranslastion = PropertyValuesHolder.ofFloat("translationX", 100f);
        valuesHolderScaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.3f);
        valuesHolderScaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.3f);
        valuesHolderColor = PropertyValuesHolder.ofInt("BackgroundColor", 0xff888888, 0xff00ff00);
        objectAnimatorProper = ObjectAnimator.ofPropertyValuesHolder(tvObjProperty, valuesHolderTranslastion, valuesHolderScaleX, valuesHolderScaleY, valuesHolderColor);
        objectAnimatorProper.setDuration(1000);

        valueAnimator = ValueAnimator.ofFloat(0,1000);
        valueAnimator.setTarget(tvValue);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (value==500f){
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(100);
                    tvValue.startAnimation(scaleAnimation);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageViewSVG.setImageResource(R.drawable.anim_svg_drawble);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alpha://透明动画
                tvAlpha.startAnimation(alphaAnimation);
                break;
            case R.id.tv_rotate://旋转动画
                tvRotate.startAnimation(rotateAnimation);
                break;
            case R.id.tv_translate: //位移动画
                tvTranslate.startAnimation(translateAnimation);
                break;
            case R.id.tv_scale: //缩放动画
                tvScale.startAnimation(scaleAnimation);
                break;
            case R.id.tv_set://动画集
                tvSet.startAnimation(animationSet);
                break;
            case R.id.tv_color:
                ObjectAnimator animatorColor = ObjectAnimator.ofInt(tvColor,"BackgroundColor",0xffff0000,0xff00ff00);
                animatorColor.setEvaluator(new ArgbEvaluator());
                animatorColor.setDuration(1000);
                animatorColor.start();
                break;
            case R.id.tv_obj:
                objectAnimator.start();
                break;
            case R.id.tv_obj_wrapper:
                objectAnimatorWrapper.start();
                break;
            case R.id.tv_obj_property:
                objectAnimatorProper.start();
                break;
            case R.id.tv_obj_property_key:
                // 在 0% 处开始向X移动
                Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
                // 时间经过 50% 的时候，动画完成度 100%
                Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);
                // 时间见过 100% 的时候，动画完成度倒退到 0%位置
                Keyframe keyframe3 = Keyframe.ofFloat(1, 0);
                PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvObjPropertyKey, holder);
                animator.start();
                break;
            case R.id.tv_value:
                valueAnimator.start();
                break;
            case R.id.tv_xml:
                Animator animatorXml = AnimatorInflater.loadAnimator(MainActivity.this,R.animator.animator_xml);
                animatorXml.setTarget(tvXml);
                animatorXml.start();
                break;
            case R.id.tv_load:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tvLoad.animate()
                            .alpha(0)
                            .y(300)
                            .setDuration(1000)
                            .withStartAction(new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            // TODO: 2018/10/24
                                        }
                                    });
                                }
                            })
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            // TODO: 2018/10/24
                                        }
                                    });
                                }
                            }).start();
                }
                break;
            case R.id.tv_custom:
                CustomAnimation  customAnimation = new CustomAnimation();
                tvCustom.startAnimation(customAnimation);
                break;
            case R.id.image_svg:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Drawable drawable = imageViewSVG.getDrawable();
                    if (drawable instanceof Animatable){
                        ((Animatable) drawable).start();
                    }
                }
                break;
        }
    }
}
