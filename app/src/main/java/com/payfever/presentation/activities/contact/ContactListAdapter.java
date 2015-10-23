package com.payfever.presentation.activities.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.rey.material.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.ContactModel;
import com.payfever.data.model.ContactStatus;
import com.payfever.presentation.PayFeverApplication;
import com.payfever.presentation.global.Constants;
import com.payfever.presentation.utils.CircleTransform;
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
            viewHolder.chStatus.setClickable(false);
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
        setStatus(_viewHolder, mContactList.get(_position));
    }

    private class ViewHolder {
        public ImageView ivAvatar;
        public CheckBox chStatus;
        public TextView tvContactName;
        public TextView tvContactPhone;
        public TextView tvContactStatus;
    }

    private void findViews(final ViewHolder _viewHolder, final View _convertView) {
        _viewHolder.ivAvatar         = (ImageView) _convertView.findViewById(R.id.rlItemContact).findViewById(R.id.ivContactAvatar_ILC);
        _viewHolder.tvContactName    = (TextView) _convertView.findViewById(R.id.rlItemContact).findViewById(R.id.tvContactName_ILC);
        _viewHolder.tvContactPhone   = (TextView) _convertView.findViewById(R.id.rlItemContact).findViewById(R.id.tvContactPhone_ILC);
        _viewHolder.chStatus         = (CheckBox) _convertView.findViewById(R.id.chContactStatus_ICL);
        _viewHolder.tvContactStatus  = (TextView) _convertView.findViewById(R.id.tvContactStatus_ICL);
    }

    private void loadAvatarFromURI(final ViewHolder _viewHolder, final String _uri) {
        Picasso.with(PayFeverApplication.getApplication())
                .load(_uri)
                .error(R.drawable.ic_avatar_contact)
                .placeholder(R.drawable.ic_avatar_contact)
                .transform(new CircleTransform())
                .into(_viewHolder.ivAvatar);
    }

    private void setStatus(final ViewHolder _viewHolder, final ContactModel _model) {
        if (_model.getStatus() == ContactStatus.UNCHECKED.getStatus()) {
            _viewHolder.chStatus.setChecked(false);
        } else if (_model.getStatus() == ContactStatus.CHECKED.getStatus()) {
            _viewHolder.chStatus.setChecked(true);
        } else if (_model.getStatus() == ContactStatus.EXPIRED.getStatus()) {
            _viewHolder.chStatus.setVisibility(View.GONE);
            _viewHolder.tvContactStatus.setVisibility(View.VISIBLE);
            _viewHolder.tvContactStatus.setText(Constants.CONTACT_STATUS_EXPIRED);
        } else if (_model.getStatus() == ContactStatus.PENDING.getStatus()) {
            _viewHolder.chStatus.setVisibility(View.GONE);
            _viewHolder.tvContactStatus.setVisibility(View.VISIBLE);
            _viewHolder.tvContactStatus.setText(Constants.CONTACT_STATUS_PENDING);
        } else if (_model.getStatus() == ContactStatus.ACCEPTED.getStatus()) {
            _viewHolder.chStatus.setVisibility(View.GONE);
            _viewHolder.tvContactStatus.setVisibility(View.VISIBLE);
            _viewHolder.tvContactStatus.setText(Constants.CONTACT_STATUS_ACCEPTED);
        }
    }
}