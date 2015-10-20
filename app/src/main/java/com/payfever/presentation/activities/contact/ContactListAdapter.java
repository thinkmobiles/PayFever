package com.payfever.presentation.activities.contact;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.model.ContactModel;
import com.payfever.presentation.PayFeverApplication;
import com.payfever.presentation.activities.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactListAdapter extends BaseAdapter {

    private List<ContactModel> mContactList;

    public void setContactList(final List<ContactModel> _contactList) {
        mContactList = _contactList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mContactList != null)
            return mContactList.size();
        else
            return 0;
    }

    @Override
    public ContactModel getItem(int position) {
        return mContactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        ViewHolder viewHolder;
        if (_convertView == null) {
            _convertView = LayoutInflater.from(PayFeverApplication.getApplication()).inflate(R.layout.item_contacts_list, _parent, false);
            viewHolder = new ViewHolder();
            findViews(viewHolder, _convertView);
            _convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) _convertView.getTag();
        }
        updateView(viewHolder, _position);
        return _convertView;
    }

    private void updateView(final ViewHolder _viewHolder, final int _position) {
        loadAvatarFromURI(_viewHolder, mContactList.get(_position).getAvatar());
        _viewHolder.tvContactName.setText(mContactList.get(_position).getName());
        _viewHolder.tvContactPhone.setText(mContactList.get(_position).getPhoneNumber());
        setStatus(_viewHolder.chStatus, mContactList.get(_position));
    }

    private class ViewHolder {
        public ImageView ivAvatar;
        public AppCompatCheckBox chStatus;
        public TextView tvContactName;
        public TextView tvContactPhone;
    }

    private void findViews(final ViewHolder _viewHolder, final View _convertView) {
        _viewHolder.ivAvatar         = (ImageView) _convertView.findViewById(R.id.ivContactAvatar_ICL);
        _viewHolder.chStatus         = (AppCompatCheckBox) _convertView.findViewById(R.id.chContactStatus_ICL);
        _viewHolder.tvContactName    = (TextView) _convertView.findViewById(R.id.tvContactName_ICL);
        _viewHolder.tvContactPhone   = (TextView) _convertView.findViewById(R.id.tvContactPhone_ICL);
    }

    private void loadAvatarFromURI(final ViewHolder _viewHolder, final String _uri) {
        Picasso.with(PayFeverApplication.getApplication())
                .load(_uri)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .transform(new CircleTransform())
                .into(_viewHolder.ivAvatar);
    }

    private void setStatus(final CheckBox _chStatus, final ContactModel _model) {
        if (_model.getStatus() == 0) {
            _chStatus.setChecked(false);
        } else if (_model.getStatus() == 1) {
            _chStatus.setChecked(true);
        }
        _chStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    _model.setStatus(1);
                } else {
                    _model.setStatus(0);
                }
            }
        });
    }
}