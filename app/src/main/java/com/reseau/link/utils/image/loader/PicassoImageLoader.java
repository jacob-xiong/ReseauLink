package com.reseau.link.utils.image.loader;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;
import com.reseau.link.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * @author by xiongyan on 2017/11/28.
 */

public class PicassoImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Picasso.with(activity)
                .load(Uri.fromFile(new File(path)))
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_default_image)
                .resize(width, height)
                .centerInside()
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Picasso.with(activity)
                .load(Uri.fromFile(new File(path)))
                .resize(width, height)
                .centerInside()
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
