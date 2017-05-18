package com.anwesome.ui.loadingcards;

import android.graphics.Bitmap;

/**
 * Created by anweshmishra on 18/05/17.
 */
public interface CardProcessor {
    Bitmap fetchBitmap();
    String fetchTitle();
    String fetchSubTitle();
}
