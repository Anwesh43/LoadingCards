package com.anwesome.ui.loadingcards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 18/05/17.
 */
public class LoadingCardView extends View {
    private boolean isLoading = true;
    private LoadingCard loadingCard = new LoadingCard();
    private Paint paint;
    public LoadingCardView(Context context, Bitmap bitmap) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(isLoading) {
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch(Exception ex) {

            }
        }
        else {

        }
    }
    public void setLoading(boolean loading) {
        isLoading = loading;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class LoadingCard {
        public void draw(Canvas canvas) {

        }
    }
}
