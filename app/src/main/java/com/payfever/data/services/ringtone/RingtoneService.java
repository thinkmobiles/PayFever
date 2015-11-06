package com.payfever.data.services.ringtone;

import com.parse.ParseObject;
import com.payfever.data.model.ringtone.Ringtone;

import java.util.List;

import rx.Observable;

/**
 * Created by richi on 2015.11.02..
 */
public interface RingtoneService {
    Observable<List<Ringtone>> getSortedRingTones();
    Observable<List<Ringtone>> getFullRingtoneList();
    Observable<List<ParseObject>> getParseRingTones();
    Observable<List<String>> getSelectedItems();
    Observable<Integer> downloadRingtone(String _url, String _filePath);
    Observable<Boolean> updateUserProfile(String _selectedItemId);
}
