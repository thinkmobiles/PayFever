package com.payfever.presentation.fragment.network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.network.NetworkUser;
import com.payfever.presentation.PayFeverApplication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkAdapter extends BaseAdapter {

    private String mFirstLevel;
    private List<NetworkUser> mData;
    private Context mContext;

    public NetworkAdapter() {
        mFirstLevel = PayFeverApplication.getApplication().getString(R.string.first_level_HMN);
        mData = new ArrayList<>();
        mContext = PayFeverApplication.getApplication();
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
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_my_network, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.ivContactAvatar_ILC);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvContactName_ILC);
            viewHolder.tvPhoneNumber = (TextView) convertView.findViewById(R.id.tvContactPhone_ILC);
            viewHolder.tvLevelIndicator = (TextView) convertView.findViewById(R.id.tvLevelIndicator_IMN);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NetworkUser user = getItem(position);

        loadAvatar("test url", viewHolder.ivAvatar);

        if (user.getUserName() != null)
            viewHolder.tvName.setText(user.getUserName());

        if (user.ismIsFirstLevel())
            viewHolder.tvLevelIndicator.setText(mFirstLevel);
        else
            viewHolder.tvLevelIndicator.setText("");

        return convertView;
    }

    private void loadAvatar(String _url, ImageView _avatar) {
        Picasso.with(mContext)
                .load(_url)
                .placeholder(R.drawable.ic_avatar_contact)
                .error(R.drawable.ic_avatar_contact)
                .fit()
                .centerCrop()
                .into(_avatar);
    }

    private static class ViewHolder {
        ImageView ivAvatar;
        TextView tvName, tvPhoneNumber, tvLevelIndicator;
    }
}
