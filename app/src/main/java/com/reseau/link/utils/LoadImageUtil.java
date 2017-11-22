package com.reseau.link.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.lang.ref.SoftReference;

import com.reseau.link.R;

/**
 * @author by xiongyan on 2017/11/20.
 * 图片加载工具类
 */

public class LoadImageUtil {
    public static int HOLDER_BIG = R.drawable.ic_launcher_background;
    public static int LOADING_PLACE_HOLDER = R.drawable.ic_launcher_background;
    public static int FAILED_PLACE_HOLDER = R.drawable.ic_launcher_background;
    private static SoftReference<Transformation> transformation;

    public static void loadProductImg(Context context, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            if (imageView != null) {
                imageView.setImageResource(FAILED_PLACE_HOLDER);
            }
            return;
        }
        Picasso.with(context)
                .load(url)
                .fit()
                .placeholder(HOLDER_BIG)
                .error(FAILED_PLACE_HOLDER)
                .into(imageView);
    }

    public static void load(Context context, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            if (imageView != null) {
                imageView.setImageResource(LOADING_PLACE_HOLDER);
            }
            return;
        }
        Picasso.with(context)
                .load(url)
                .fit()
                .placeholder(LOADING_PLACE_HOLDER)
                .error(FAILED_PLACE_HOLDER)
                .into(imageView);
    }

    public static void loadIcon(Context context, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            if (imageView != null) {
                imageView.setImageResource(LOADING_PLACE_HOLDER);
            }
            return;
        }
        Picasso.with(context)
                .load(url)
                .fit()
                .placeholder(LOADING_PLACE_HOLDER)
                .error(FAILED_PLACE_HOLDER)
                .into(imageView);
    }


    public static void loadIcon(int width, int high, Context context, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            if (imageView != null) {
                imageView.setImageResource(LOADING_PLACE_HOLDER);
            }
            return;
        }
        Picasso.with(context)
                .load(url + "@" + width + "w_" + high + "h")
                .fit()
                .placeholder(LOADING_PLACE_HOLDER)
                .error(FAILED_PLACE_HOLDER)
                .into(imageView);
    }


    public static void load(Context context, String url, ImageView imageView, int loadingDrawable, int failedDrawable, int width, int height) {
        if (TextUtils.isEmpty(url)) {
            if (imageView != null) {
                imageView.setImageResource(LOADING_PLACE_HOLDER);
            }
            return;
        }
        Picasso.with(context)
                .load(url)
//                .resize(width, height)
                .fit()
                .placeholder(loadingDrawable)
                .error(failedDrawable)
                .into(imageView);
    }

    public static void load(Context context, String url, ImageView imageView, int width, int height) {
        load(context, url, imageView, LOADING_PLACE_HOLDER, FAILED_PLACE_HOLDER, width, height);
    }

    public static void loadIcon(Context context, String url, ImageView imageView, int width, int height) {
        load(context, url, imageView, LOADING_PLACE_HOLDER, FAILED_PLACE_HOLDER, width, height);
    }


    public static void loadResize(final Context context, final String url, final ImageView imageView) {
        if (transformation == null || transformation.get() == null) {
            transformation = new SoftReference<Transformation>(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int targetWidth = imageView.getWidth();
                    if (source.getWidth() == 0) {
                        return source;
                    } else {
                        double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                        int targetHeight = (int) (targetWidth * aspectRatio);
                        if (targetHeight != 0 && targetWidth != 0) {
                            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                            if (result != source) {
                                source.recycle();
                            }
                            return result;
                        } else {
                            return source;
                        }
                    }

                }

                @Override
                public String key() {
                    return "transformation desiredWidth";
                }
            });
        }
        Picasso.with(context)
                .load(url)
                .placeholder(LOADING_PLACE_HOLDER)
                .error(FAILED_PLACE_HOLDER)
                .transform(transformation.get())
                .into(imageView);
    }

    public static void loadBigImage(final Context context, final String url, final ImageView imageView, final int xScreen, Callback callback) {
        Transformation myTransformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = imageView.getWidth() * xScreen;
                if (source.getWidth() == 0) {
                    return source;
                }
                if (source.getWidth() < targetWidth) {
                    return source;
                } else {
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int targetHeight = (int) (targetWidth * aspectRatio);
                    if (targetHeight != 0 && targetWidth != 0) {
                        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                        if (result != source) {
                            source.recycle();
                        }
                        return result;
                    } else {
                        return source;
                    }
                }

            }

            @Override
            public String key() {
                return "transformation desiredWidth";
            }
        };
        Picasso.with(context)
                .load(url)
                .placeholder(LOADING_PLACE_HOLDER)
                .error(FAILED_PLACE_HOLDER)
                .transform(myTransformation)
                .into(imageView,callback);
    }

    public static void loadMatchHeight(final Context context, final String url, final ImageView imageView) {
        Transformation myTransformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetHeight = imageView.getHeight();
                if (source.getHeight() == 0) {
                    return source;
                }
                if (source.getHeight() < targetHeight) {
                    return source;
                } else {
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int targetWidth = (int) ((double) targetHeight / aspectRatio);
                    if (targetWidth != 0 && targetHeight != 0) {
                        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                        if (result != source) {
                            source.recycle();
                        }
                        return result;
                    } else {
                        return source;
                    }
                }

            }

            @Override
            public String key() {
                return "transformation desiredWidth";
            }
        };

        Picasso.with(context)
                .load(url)
                .placeholder(LOADING_PLACE_HOLDER)
                .error(FAILED_PLACE_HOLDER)
                .transform(myTransformation)
                .into(imageView);
    }
}
