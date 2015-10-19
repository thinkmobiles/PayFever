package com.payfever.data.services.statistic;

import com.parse.ParseObject;
import com.payfever.data.api.statistic_api.StatisticApi;
import com.payfever.data.api.statistic_api.StatisticApiImpl;
import com.payfever.data.model.StatisticModel;
import com.payfever.data.transformators.BaseTransformation;
import com.payfever.data.transformators.StatisticTransformatorImpl;
import com.payfever.data.services.statistic.StatisticService;

import java.text.ParseException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by richi on 2015.10.19..
 */
public class StatisticServiceImpl implements StatisticService {

    private BaseTransformation<StatisticModel, ParseObject> mTransformation;
    private StatisticApi mStatisticApi;

    public StatisticServiceImpl() {
        mTransformation = new StatisticTransformatorImpl();
        mStatisticApi = new StatisticApiImpl();
    }

    @Override
    public Observable<StatisticModel> getStatisticData() {
        return Observable.create(new Observable.OnSubscribe<StatisticModel>() {
            @Override
            public void call(Subscriber<? super StatisticModel> subscriber) {
                //TODO: call through ParseQuery
                ParseObject statisticData = null;
                try {
                    statisticData = mStatisticApi.getStatisticData();
                    StatisticModel model = mTransformation.transform(statisticData);
                    subscriber.onNext(model);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }
}
