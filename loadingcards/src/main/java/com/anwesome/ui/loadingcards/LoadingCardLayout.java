package com.anwesome.ui.loadingcards;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 18/05/17.
 */
public class LoadingCardLayout extends ViewGroup{
    private int w,h;
    private CardLoader cardLoader;
    private LoadingCardAnimator animator;
    public void startCardLoader() {
        cardLoader.start();
        animator.start();
    }
    public LoadingCardLayout(Context context) {
        super(context);
        initDimension(context);
        cardLoader = new CardLoader((Activity) context);
        animator = new LoadingCardAnimator((Activity)context);
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void addLoadingCard(CardProcessor cardProcessor) {
        LoadingCardView loadingCardView = new LoadingCardView(getContext());
        loadingCardView.setCardProcessor(cardProcessor);
        addView(loadingCardView,new LayoutParams(w,w));
        requestLayout();
        cardLoader.addView(loadingCardView);
        animator.addView(loadingCardView);
    }
    public void onMeasure(int wspec,int hspec) {
        int hNew = h/60;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            hNew+=h/40+child.getMeasuredHeight();
        }
        setMeasuredDimension(w,Math.max(hNew+h/5,h));
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int y = h/60;
        for(int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            child.layout(0,y,child.getMeasuredWidth(),y+child.getMeasuredHeight());
            y+=child.getMeasuredHeight()+h/40;
        }
    }
}
