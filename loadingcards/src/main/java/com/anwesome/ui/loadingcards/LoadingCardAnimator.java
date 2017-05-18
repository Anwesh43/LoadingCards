package com.anwesome.ui.loadingcards;

import android.app.Activity;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 18/05/17.
 */
public class LoadingCardAnimator {
    private Activity activity;
    private boolean isRunning = true;
    private ConcurrentLinkedQueue<LoadingCardView> loadingCardViews = new ConcurrentLinkedQueue<>();
    public LoadingCardAnimator(Activity activity) {
        this.activity = activity;
    }
    public void addView(LoadingCardView loadingCardView) {
        this.loadingCardViews.add(loadingCardView);
    }
    public void start() {
        LCARunner runner = new LCARunner();
        Thread animatorThread = new Thread(runner);
        animatorThread.start();
    }
    private class LCARunner implements Runnable {
        public void run() {
            while(loadingCardViews.size()>0) {
                for(LoadingCardView loadingCardView:loadingCardViews) {
                    loadingCardView.postInvalidate();
                    if(loadingCardView.isLoaded()) {
                        loadingCardViews.remove(loadingCardView);
                    }
                }
                try {
                    Thread.sleep(50);
                }
                catch (Exception ex) {

                }
            }
        }
    }
}
