package com.payfever.data.services.ringtone;

import com.parse.ParseObject;
import com.payfever.data.model.ringtone.Ringtone;

import java.util.List;

import rx.Observable;

/**
 * Created by richi on 2015.11.02..
 */
public interface RingtoneService {
    Observable<List<Ringtone>> getRingTones();
    Observable<List<ParseObject>> getParseRingTones();
    Observable<Integer> downloadRingtone(String _url, String _filePath);
}
