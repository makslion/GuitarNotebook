package com.maksym.guitarnotebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DetailsViewPagerAdapter extends PagerAdapter
{
    private Context mContext;       //m for member - private variable
    private String[] mImageURLS;


    public DetailsViewPagerAdapter(Context context)
    {
        mContext = context;
        //Log.d("DetailsAdapter", "Adapter created");
    }


    public void setImages(String[] ImageURLS) {
        mImageURLS = ImageURLS;
    }


    @Override
    public int getCount()
    {
        if (mImageURLS != null)
            return mImageURLS.length;
        else
            return 0;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //Log.d("DetailsAdapter", "Attempt to download image "+mImageURLS[position]);

        new DownloadImage(imageView).execute(mImageURLS[position]);

        container.addView(imageView, 0);
        return imageView;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((ImageView) object);
    }
}
