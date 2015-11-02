package com.payfever.data.transformators.ringtones;

import com.parse.ParseObject;
import com.payfever.data.model.ringtone.Ringtone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richi on 2015.11.02..
 */
public class RingtoneTransformatorImpl implements RingtoneTranformator {
    @Override
    public List<Ringtone> transform(List<ParseObject> _data) {
        List<Ringtone> ringtoneList = new ArrayList<>();
        for (ParseObject parseObject : _data) {
            Ringtone ringtone = new Ringtone();
            ringtone.setmObjectId(parseObject.getObjectId());
            ringtone.setmName(parseObject.getString("fileRole"));
            ringtone.setmRevenue(parseObject.getInt("revenue"));
            if (parseObject.getParseFile("currentFile") != null)
                ringtone.setmUrlToFile(parseObject.getParseFile("currentFile").getUrl());

            ringtoneList.add(ringtone);
        }

        return ringtoneList;
    }
}
