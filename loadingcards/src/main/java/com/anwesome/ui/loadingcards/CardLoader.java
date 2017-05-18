package com.anwesome.ui.loadingcards;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 18/05/17.
 */
public class CardLoader {
    private Activity activity;
    public CardLoader(Activity activity) {
        this.activity = activity;
    }
    private ConcurrentLinkedQueue<LoadingCardView> loadingCardViews = new ConcurrentLinkedQueue<>();
    public void addView(LoadingCardView loadingCardView) {
        this.loadingCardViews.add(loadingCardView);
    }
    public void start() {
        CardLoaderRunner runner = new CardLoaderRunner();
        Thread thread = new Thread(runner);
        thread.start();
    }
    private class CardLoaderRunner implements Runnable {
        public void run() {
            for(LoadingCardView loadingCardView:loadingCardViews) {
                final LoadingCardView cardView = loadingCardView;
                CardProcessor cardProcessor = loadingCardView.getCardProcessor();
                if(cardProcessor!=null) {
                    final Bitmap bitmap = cardProcessor.fetchBitmap();
                    final String title = cardProcessor.fetchTitle();
                    final String subtitle = cardProcessor.fetchTitle();
                    if(bitmap!=null && title!=null && subtitle!=null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cardView.setCardProperties(bitmap,title,subtitle);
                            }
                        });

                    }
                }
            }
        }
    }
}
