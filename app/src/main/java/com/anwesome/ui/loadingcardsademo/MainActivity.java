package com.anwesome.ui.loadingcardsademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.loadingcards.LoadingCardList;
import com.anwesome.ui.loadingcards.LoadingCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        LoadingCardView loadingCardView = new LoadingCardView(this);
//        addContentView(loadingCardView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
        LoadingCardList loadingCardList = new LoadingCardList(this);
        for(int i=0;i<4;i++) {
            loadingCardList.addLoadingCard(null);
        }
        loadingCardList.show();
    }
}
