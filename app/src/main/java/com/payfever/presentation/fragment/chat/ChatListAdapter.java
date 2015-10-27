package com.payfever.presentation.fragment.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.ChatModel;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatListAdapter extends BaseAdapter {

    private List<ChatModel> mChatList;

    public void setChatList(final List<ChatModel> _list) {
        mChatList = _list;
    }

    @Override
    public int getCount() {
        if (mChatList != null)
            return mChatList.size();
        else
            return 0;
    }

    @Override
    public ChatModel getItem(int position) {
        if (mChatList != null)
            return mChatList.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        ViewHolder viewHolder;
        if (_convertView == null) {
            _convertView = LayoutInflater.from(PayFeverApplication.getApplication()).inflate(R.layout.item_chat_list, _parent, false);
            viewHolder = new ViewHolder();
            findViews(viewHolder, _convertView);
            _convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) _convertView.getTag();
        }
        updateView(viewHolder, _position);
        return _convertView;
    }

    private class ViewHolder {
        public ImageView ivAvatar;
        public TextView tvUserName;
//        public TextView tvUserPhone;
        public TextView tvDecription;
        public TextView tvDate;
    }

    private void findViews(final ViewHolder _viewHolder, final View _convertView) {
        _viewHolder.ivAvatar         = (ImageView) _convertView.findViewById(R.id.rlChatContact_FCL).findViewById(R.id.ivContactAvatar_ILC);
        _viewHolder.tvUserName    = (TextView) _convertView.findViewById(R.id.rlChatContact_FCL).findViewById(R.id.tvContactName_ILC);
        _viewHolder.tvDecription   = (TextView) _convertView.findViewById(R.id.rlChatContact_FCL).findViewById(R.id.tvContactPhone_ILC);
        _viewHolder.tvDate         = (TextView) _convertView.findViewById(R.id.tvDate_FCL);
    }

    private void updateView(final ViewHolder _viewHolder, final int _position) {
        _viewHolder.tvUserName.setText(mChatList.get(_position).getUserName());
        _viewHolder.tvDecription.setText(mChatList.get(_position).getDecription());
//        _viewHolder.tvDate.setText(mChatList.get(_position).getDate());
    }
}
