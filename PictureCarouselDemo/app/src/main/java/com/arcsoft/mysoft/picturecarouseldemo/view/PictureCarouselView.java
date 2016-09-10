package com.arcsoft.mysoft.picturecarouseldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by yhp5210 on 2016/9/9.
 */
public class PictureCarouselView extends RelativeLayout {

    private List<ImageView> imageViews;
    private List<String> titles;
    private int imgCount;

    public PictureCarouselView(Context context) {
        super(context);
    }
    public PictureCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public PictureCarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
