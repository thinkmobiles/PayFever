package com.payfever.data.transformators;

/**
 * Created by richi on 2015.10.19..
 */
public interface BaseTransformation<T,V> {
    T transform(V _data);
}
