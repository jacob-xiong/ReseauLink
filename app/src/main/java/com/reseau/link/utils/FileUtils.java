package com.reseau.link.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * @author by xiongyan on 2017/11/30.
 */

public class FileUtils {
    private static final String RULE_CONTENT = "content";
    private static final String RULE_FILE = "file";


    public static String getPath(Context context, Uri uri) {

        if (RULE_CONTENT.equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if (RULE_FILE.equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * 获取录音文件路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getAudioFilePathFromUri(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver()
                .query(uri, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        return cursor.getString(index);
    }

    /**
     * 打开原生文件选择器
     */
    public static void showFileChooser(Context context) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            ((Activity) context).startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), Constant.REQUEST_FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            MyToast.showShort(context, "请安装文件管理器");
        }
    }
}
