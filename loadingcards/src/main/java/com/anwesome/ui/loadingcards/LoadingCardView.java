package com.anwesome.ui.loadingcards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 18/05/17.
 */
public class LoadingCardView extends View {
    private boolean isLoading = true;
    private LoadingCard loadingCard;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int w,h,time = 0;
    private CardProcessor cardProcessor;
    private Card card = new Card();
    public void setCardProcessor(CardProcessor cardProcessor) {
        this.cardProcessor = cardProcessor;
    }
    public CardProcessor getCardProcessor() {
        return cardProcessor;
    }
    public LoadingCardView(Context context) {
        super(context);
    }
    public void setCardProperties(Bitmap bitmap,String title,String subTitle) {
        card.setProperties(bitmap,title,subTitle);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            loadingCard = new LoadingCard();
        }
        canvas.drawColor(Color.WHITE);
        if(isLoading) {
            loadingCard.draw(canvas);
            loadingCard.update();
            try {
                Thread.sleep(10);
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
            if(factor>1.4f) {
                factor = 0;
            }
            imageBlock.update(factor);
            titleBlock.update(factor);
            subTitleBlock.update(factor);
        }
        public PointF getBitmapSize() {
            return new PointF(imageBlock.w,imageBlock.h);
        }
        public PointF getBitmapXY() {
            return new PointF(imageBlock.x,imageBlock.y);
        }
        public PointF getTitleXY() {
            return new PointF(titleBlock.x,titleBlock.y);
        }
        public PointF getSubtitleXY() {
            return new PointF(subTitleBlock.x,subTitleBlock.y);
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
            paint.setColor(Color.parseColor("#99E0E0E0"));
            canvas.save();
            canvas.translate(x,y);
            Path path = new Path();
            path.addRect(new RectF(0,0,w*1.05f,h), Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.drawRect(new RectF(0,0,w,h),paint);
            paint.setColor(Color.parseColor("#11000000"));
            canvas.drawRect(new RectF(mx,0,mx+w/4,h),paint);
            canvas.restore();
        }
        public void update(float factor) {
            mx = w*factor;
        }
    }
    private class Card {
        private Bitmap bitmap;
        private String title,subtitle;
        public void setProperties(Bitmap bitmap,String title,String subTitle) {
            this.bitmap = bitmap;
            this.title = title;
            this.subtitle = subTitle;
            PointF bitmapSize = loadingCard.getBitmapSize();
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)bitmapSize.x,(int)bitmapSize.y,true);
            isLoading = false;
        }
        public void drawCanvas(Canvas canvas) {
            PointF bitmapPosition = loadingCard.getBitmapSize();
            canvas.drawBitmap(bitmap,bitmapPosition.x,bitmapPosition.y,paint);
            paint.setTextSize(h/10);
            PointF titlePosition = loadingCard.getTitleXY();
            canvas.drawText(trimText(title,w/2),titlePosition.x,titlePosition.y,paint);
            paint.setTextSize(h/20);
            PointF subTitlePosition = loadingCard.getTitleXY();
            canvas.drawText(trimText(subtitle,w/4),subTitlePosition.x,subTitlePosition.y,paint);
        }
        private String trimText(String text,float w) {
            String msg = "";
            for(int i=0;i<text.length();i++) {
                char ch = text.charAt(i);
                msg = msg+ch;
                if(paint.measureText(msg) > w) {
                    msg = msg+"...";
                }
            }
            return msg;
        }
    }
}
