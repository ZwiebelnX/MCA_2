package com.zwiebelnx.mca_2.Anim;

import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class AllAnim {
    /*
    Flag
     */
    public static final int DIRECTION_FROM_BUTTOM = 0;
    public static final int DIRECTION_FROM_RIGHT = 1;
    public static final int DIRECTION_FROM_CENTER = 2;
    /*
    物体出现的过渡动画
     */
    public static AnimationSet showUp(long delayTime,int Direction){

        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translate;
        switch (Direction){
            case 0:{
                translate = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF,0f, TranslateAnimation.RELATIVE_TO_SELF,0f,
                        TranslateAnimation.RELATIVE_TO_SELF,2f, TranslateAnimation.RELATIVE_TO_SELF,0f);
                translate.setDuration(2000);
            }
            break;

            case 1:{
                translate = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF,2f, TranslateAnimation.RELATIVE_TO_SELF,0f,
                        TranslateAnimation.RELATIVE_TO_SELF,0f, TranslateAnimation.RELATIVE_TO_SELF,0f);
                translate.setDuration(2000);
            }
            break;

            case 2:{
                translate = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF,2f, TranslateAnimation.RELATIVE_TO_SELF,0f,
                        TranslateAnimation.RELATIVE_TO_SELF,0f, TranslateAnimation.RELATIVE_TO_SELF,0f);
                translate.setDuration(2000);
            }
            break;
            default:{
                translate = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF,2f, TranslateAnimation.RELATIVE_TO_SELF,0f,
                        TranslateAnimation.RELATIVE_TO_SELF,0f, TranslateAnimation.RELATIVE_TO_SELF,0f);
                translate.setDuration(2000);
            }
            break;
        }
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        alpha.setDuration(2000);
        translate.setStartOffset(delayTime);
        alpha.setStartOffset(delayTime);
        animationSet.addAnimation(translate);
        animationSet.addAnimation(alpha);
        return animationSet;
    }
}
