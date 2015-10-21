package com.payfever.presentation.fragment.network;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfever.data.model.UserModel;
import com.payfever.data.model.network.NetworkResponse;
import com.payfever.data.model.network.NetworkUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkAdapter extends BaseAdapter {

    private List<NetworkUser> mData;

    public NetworkAdapter() {
        mData = new ArrayList<>();
    }

    public void setData(List<NetworkUser> _data) {
        mData = _data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public NetworkUser getItem(int _position) {
        return mData.get(_position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    private static class ViewHolder {
        ImageView ivAvatar;
        TextView tvFirstName, tvLastName, tvLevelIndicator;
    }
}
