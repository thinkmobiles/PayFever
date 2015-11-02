package com.payfever.data.api.ringtone_api;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by richi on 2015.11.02..
 */
public class RingtoneApiImpl implements RingtoneApi {

    @Override
    public List<ParseObject> getRingtones() throws ParseException {
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("FileContainer");
        List<ParseObject> ringTones = parseQuery.find();
        return ringTones;
    }

    @Override
    public String getUrl(String _id) throws ParseException {
        String url = "";
        ParseQuery<ParseObject> query = ParseQuery.getQuery("FileContainer");
        ParseObject parseObject = query.get(_id);
        ParseFile parseFile = parseObject.getParseFile("currentFile");
        if (parseFile != null)
            url = parseFile.getUrl();

        return url;
    }
}
