package com.anwesome.ui.loadingcardsademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.loadingcards.CardProcessor;
import com.anwesome.ui.loadingcards.LoadingCardList;
import com.anwesome.ui.loadingcards.LoadingCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        LoadingCardView loadingCardView = new LoadingCardView(this);
//        addContentView(loadingCardView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stp);
        LoadingCardList loadingCardList = new LoadingCardList(this);
        for(int i=0;i<4;i++) {
            final int index = i+1;
            loadingCardList.addLoadingCard(new CardProcessor() {
                @Override
                public Bitmap fetchBitmap() {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (Exception ex) {

                    }
                    return bitmap;
                }

                @Override
                public String fetchTitle() {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (Exception ex) {

                    }
                    return "title "+index;
                }

                @Override
                public String fetchSubTitle() {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (Exception ex) {

                    }
                    return "subtitle "+index;
                }
            });
        }
        loadingCardList.show();
    }
}
