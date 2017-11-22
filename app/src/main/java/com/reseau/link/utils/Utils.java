package com.reseau.link.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.reseau.link.R;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class Utils {
    public static Intent getIntent(Context context, int host) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getUri(context, host)));
        return intent;
    }
    public static String getUri(Context context, int host) {
        StringBuilder builder = new StringBuilder(context.getResources().getString(R.string.scheme));
        builder.append("://");
        builder.append(context.getResources().getString(host));

        builder.append("/");
        return builder.toString();
    }
}
