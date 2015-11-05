package com.payfever.data.transformators.ringtones;

import com.parse.ParseObject;
import com.payfever.data.model.ringtone.Ringtone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by richi on 2015.11.02..
 */
public class RingToneTransformatorImpl implements RingtoneTranformator {

    @Override
    public List<Ringtone> transform(List<ParseObject> _data) {
        List<Ringtone> ringtoneList = new ArrayList<>();
        for (ParseObject parseObject : _data) {
            Ringtone ringtone = new Ringtone();
            ringtone.setObjectId(parseObject.getObjectId());
            ringtone.setName(parseObject.getString("fileRole"));
            ringtone.setRevenue(parseObject.getInt("revenue"));
            if (parseObject.getParseFile("currentFile") != null)
                ringtone.setUrlToFile(parseObject.getParseFile("currentFile").getUrl());

            ringtoneList.add(ringtone);
        }

        return ringtoneList;
    }

    @Override
    public List<Ringtone> checkRingTonesAndShowIfNeed(List<Ringtone> _ringtone,
                                                      List<String> _selectedElements) {
        Iterator<Ringtone> iterator = _ringtone.iterator();
        while (iterator.hasNext()) {
            Ringtone ringtone = iterator.next();
            if (_selectedElements.contains(ringtone.getObjectId()))
                iterator.remove();
        }
        return _ringtone;
    }
}
