package com.payfever.data.api.ringtone_api;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.io.InputStream;
import java.util.List;

/**
 * Created by richi on 2015.11.02..
 */
public interface RingtoneApi {
    List<ParseObject> getRingtones() throws ParseException;
    String getUrl(String _id) throws ParseException;
}
