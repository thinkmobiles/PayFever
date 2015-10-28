package com.payfever.presentation.activities.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.payfever.R;
import com.payfever.data.model.ChatModel;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public final class ChatAdapter extends BaseAdapter {

    private List<ChatModel> mChatModelList;

    public void setChatModelList(final List<ChatModel> _chatModelList) {
        this.mChatModelList = _chatModelList;
    }

    public ChatAdapter() {
    }

    @Override
    public int getCount() {
        if (mChatModelList != null)
            return mChatModelList.size();
        else
            return 0;
    }

    @Override
    public ChatModel getItem(int position) {
         return mChatModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int _position) {
        if (getItem(_position).getUserName().equals(ParseUser.getCurrentUser().getUsername())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        ViewHolder viewHolder;
        if (_convertView == null) {
            if (getItemViewType(_position) == 0) {
                _convertView = LayoutInflater.from(PayFeverApplication.getApplication()).inflate(R.layout.item_chat_outgoing, _parent, false);
            } else if (getItemViewType(_position) == 1) {
                _convertView = LayoutInflater.from(PayFeverApplication.getApplication()).inflate(R.layout.item_chat_incoming, _parent, false);
            }
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
        //        public ImageView ivAvatar;
        public TextView tvUserName;
        //        public TextView tvUserPhone;
        public TextView tvDecription;
        public TextView tvDate;
    }

    private void findViews(final ViewHolder _viewHolder, final View _convertView) {
//        _viewHolder.ivAvatar         = (ImageView) _convertView.findViewById(R.id.rlChatContact_FCL).findViewById(R.id.ivContactAvatar_ILC);
        _viewHolder.tvUserName    = (TextView) _convertView.findViewById(R.id.tvUserName);
        _viewHolder.tvDecription   = (TextView) _convertView.findViewById(R.id.tvChatText);
        _viewHolder.tvDate         = (TextView) _convertView.findViewById(R.id.tvDate);
    }

    private void updateView(final ViewHolder _viewHolder, final int _position) {
        _viewHolder.tvUserName.setText(mChatModelList.get(_position).getUserName());
        _viewHolder.tvDecription.setText(mChatModelList.get(_position).getDescription());
        _viewHolder.tvDate.setText(mChatModelList.get(_position).getDate());
    }
}
