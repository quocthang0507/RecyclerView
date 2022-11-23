package com.example.RecyclerView.Classes;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Utils {
    public static String formatCurrency(int number) {
        NumberFormat format = new DecimalFormat("#,###");
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance(Locale.US));//Or default locale
        String t = format.format(number);
        t = t.replace(',', '.');
        return t + "₫";
    }

    public static String getImageFilePath(Context context, Uri uri) {
        File file = new File(uri.getPath());
        String[] filePath = file.getPath().split(":");
        String image_id = filePath[filePath.length - 1];

        Cursor cursor = context.getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor != null && cursor.moveToFirst()) {
                String path = cursor.getString(column_index);
                cursor.close();
                return path;
            }
            return null;
        }
        return null;
    }
}
