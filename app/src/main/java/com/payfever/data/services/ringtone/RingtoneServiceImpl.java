package com.payfever.data.services.ringtone;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.payfever.data.api.ringtone_api.RingtoneApi;
import com.payfever.data.api.ringtone_api.RingtoneApiImpl;
import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.data.transformators.ringtones.RingtoneTranformator;
import com.payfever.data.transformators.ringtones.RingToneTransformatorImpl;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by richi on 2015.11.02..
 */
public class RingtoneServiceImpl implements RingtoneService {

    private final RingtoneApi mApi;
    private final RingtoneTranformator mTransformator;

    public RingtoneServiceImpl() {
        mApi = new RingtoneApiImpl();
        mTransformator = new RingToneTransformatorImpl();
    }

    @Override
    public Observable<List<Ringtone>> getSortedRingTones() {
        return Observable.zip(getFullRingtoneList(), getSelectedItems(),
                new Func2<List<Ringtone>, List<String>, List<Ringtone>>() {
            @Override
            public List<Ringtone> call(List<Ringtone> ringTones, List<String> _selectedItems) {
                return mTransformator.checkRingTonesAndShowIfNeed(ringTones, _selectedItems);
            }
        });
    }

    @Override
    public Observable<List<Ringtone>> getFullRingtoneList() {
        return getParseRingTones()
                .map(new Func1<List<ParseObject>, List<Ringtone>>() {
                    @Override
                    public List<Ringtone> call(List<ParseObject> parseObjects) {
                        return mTransformator.transform(parseObjects);
                    }
                });
    }

    @Override
    public Observable<List<String>> getSelectedItems() {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                ParseUser parseUser = ParseUser.getCurrentUser();
                try {
                    parseUser.fetch();
                    List<String> selectedItems = parseUser.getList("selectedFileItems");
                    subscriber.onNext(selectedItems);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }

    @Override
    public Observable<List<ParseObject>> getParseRingTones() {
        return Observable.create(new Observable.OnSubscribe<List<ParseObject>>() {
            @Override
            public void call(Subscriber<? super List<ParseObject>> subscriber) {
                try {
                    subscriber.onNext(mApi.getRingTones());
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }

    @Override
    public Observable<Integer> downloadRingtone(final String _url, final String _filePath) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                int count, last = -1;
                try {
                    URL url = new URL(_url);
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    int size = connection.getContentLength();

                    InputStream input = connection.getInputStream();
                    OutputStream output = new FileOutputStream(_filePath + ".mp3");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(output);
                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        total += count;
                        int progress = (int) ((total * 100) / size);
                        bufferedOutputStream.write(data, 0, count);
                        if (last != progress) {
                            last = progress;
                            subscriber.onNext(progress);
                        }
                    }

                    output.flush();
                    output.close();
                    input.close();
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }

    @Override
    public Observable<Boolean> updateUserProfile(final String _selectedItemId) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                ParseUser parseUser = ParseUser.getCurrentUser();
                try {
                    parseUser.fetch();
                    parseUser.addUnique("selectedFileItems", _selectedItemId);
                    parseUser.save();
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }
}
