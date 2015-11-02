package com.payfever.data.transformators.network;

import com.parse.ParseObject;
import com.payfever.data.model.network.NetworkUserResponse;
import com.payfever.data.transformators.BaseTransformation;

import java.util.List;
import java.util.Map;

/**
 * Created by richi on 2015.11.02..
 */
public interface NetworkTransformator extends BaseTransformation<NetworkUserResponse, Map<String, Object>> {

}
