package com.payfever.data.transformators.ringtones;

import com.parse.ParseObject;
import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.data.transformators.BaseTransformation;

import java.util.List;

/**
 * Created by richi on 2015.11.02..
 */
public interface RingtoneTranformator extends BaseTransformation<List<Ringtone>, List<ParseObject>> {
    List<Ringtone> checkRingTonesAndShowIfNeed(List<Ringtone> _ringtone, List<String> _id);
}
