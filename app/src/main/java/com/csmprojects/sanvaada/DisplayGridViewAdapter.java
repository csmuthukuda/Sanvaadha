package com.csmprojects.sanvaada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import pl.droidsonroids.gif.transforms.CornerRadiusTransform;
import pl.droidsonroids.gif.transforms.Transform;

/**
 * Created by chathuranga on 7/23/17.
 */
public class DisplayGridViewAdapter extends BaseAdapter {


    private Context context;
private List<GifItem> items;
    DisplayGridViewAdapter(Context context, List<GifItem> items){
        this.context=context;
this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //  ImageView imageView;
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(130, 130));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
//        Glide.with(context).load((items.get(position).getGifId())).asGif().transform(new GifDrawableTransformation(new CropCircleTransformation(context),
//                Glide.get(context).getBitmapPool())).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(imageView);

        pl.droidsonroids.gif.GifDrawable firstDrawable = pl.droidsonroids.gif.GifDrawable.createFromResource(context.getResources(),
                (items.get(position)).getGifId());
        firstDrawable.setLoopCount(0);
        Transform transform = new CornerRadiusTransform(Main.RADIUS);
        firstDrawable.setTransform(transform);
        imageView.setImageDrawable(firstDrawable);

        return imageView;

    }

//    private int []gifArray={R.drawable.write,R.drawable.work,R.drawable.study,R.drawable.sweep,
//            R.drawable.tear,R.drawable.understand,R.drawable.walk,R.drawable.wash};
}
