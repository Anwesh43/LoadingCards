package com.anwesome.ui.loadingcards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 18/05/17.
 */
public class LoadingCardView extends View {
    private boolean isLoading = true;
    private LoadingCard loadingCard;
    private Paint paint;
    private int w,h,time = 0;
    public LoadingCardView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            loadingCard = new LoadingCard();
        }
        if(isLoading) {
            loadingCard.draw(canvas);
            loadingCard.update();
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch(Exception ex) {

            }
        }
        else {

        }
        time++;
    }
    public void setLoading(boolean loading) {
        isLoading = loading;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class LoadingCard {
        private float factor = 0;
        private LoadingBlock imageBlock,titleBlock,subTitleBlock;
        public LoadingCard() {
            imageBlock = new LoadingBlock(w/10,h/20,4*w/5,3*h/5);
            titleBlock = new LoadingBlock(w/10,7*h/10,w/2,h/10);
            subTitleBlock = new LoadingBlock(w/10,17*h/20,w/3,h/10);
        }
        public void draw(Canvas canvas) {
            imageBlock.draw(canvas);
            titleBlock.draw(canvas);
            subTitleBlock.draw(canvas);
        }
        public void update() {
            factor+=0.1f;
            if(factor>1) {
                factor = 0;
            }
        }
    }
    private class LoadingBlock {
        private float x,y,w,h,mx = 0;
        public LoadingBlock(float x,float y,float w,float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.mx = 0;
        }
        public void draw(Canvas canvas) {
            paint.setColor(Color.parseColor("#E0E0E0"));
            canvas.save();
            canvas.translate(x,y);
            canvas.drawRect(new RectF(0,0,w,h),paint);
            paint.setColor(Color.parseColor("#757575"));
            canvas.drawRect(new RectF(mx,0,mx+w/10,h),paint);
            canvas.restore();
        }
        public void update(float factor) {
            mx = w*factor;
        }
    }
}
