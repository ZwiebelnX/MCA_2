package com.zwiebelnx.mca_2.Anim;

import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class AllAnim {
    /*
    物体出现的过渡动画
     */
    public static AnimationSet showUp(long delayTime){
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f, TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,0f, TranslateAnimation.RELATIVE_TO_SELF,0f);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        translate.setDuration(2000);
        alpha.setDuration(2000);
        translate.setStartOffset(delayTime);
        alpha.setStartOffset(delayTime);
        animationSet.addAnimation(translate);
        animationSet.addAnimation(alpha);
        return animationSet;
    }
}
