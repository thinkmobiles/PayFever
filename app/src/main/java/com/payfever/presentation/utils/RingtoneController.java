package com.payfever.presentation.utils;

import android.content.ContentValues;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;

import com.payfever.presentation.PayFeverApplication;

import java.io.File;

/**
 * Created by
 * mRogach on 03.11.2015.
 */

public final class RingtoneController {

    public static void setRingtone(final String _path, final String _name) {

        Context context = PayFeverApplication.getApplication();

        File file = new File(_path, _name);

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, _name);
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);

        Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
        Uri newUri = context.getContentResolver().insert(uri, values);

        try {
            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE, newUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
