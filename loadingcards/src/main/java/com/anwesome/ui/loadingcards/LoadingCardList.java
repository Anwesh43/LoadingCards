package com.anwesome.ui.loadingcards;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 18/05/17.
 */
public class LoadingCardList {
    private Activity activity;
    private ScrollView scrollView;
    private boolean isShown = false;
    private LoadingCardLayout loadingCardLayout;
    public LoadingCardList(Activity activity) {
        this.activity = activity;
        scrollView = new ScrollView(activity);
        loadingCardLayout = new LoadingCardLayout(activity);
        scrollView.addView(loadingCardLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
    public void addLoadingCard(CardProcessor cardProcessor) {
        if(!isShown) {
            loadingCardLayout.addLoadingCard(cardProcessor);
        }
    }
}
