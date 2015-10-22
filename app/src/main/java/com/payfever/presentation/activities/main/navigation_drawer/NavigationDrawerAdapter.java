package com.payfever.presentation.activities.main.navigation_drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

/**
 * Created by
 * mRogach on 22.10.2015.
 */

public final class NavigationDrawerAdapter extends BaseAdapter {

    private List<NavDrawerItem> menuItems;

    public void setMenuItems(final List<NavDrawerItem> _contactList) {
        menuItems = _contactList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (menuItems != null) {
            return menuItems.size();
        } else
            return 0;
    }

    @Override
    public NavDrawerItem getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        ViewHolder viewHolder;
        if (_convertView == null) {
            _convertView = LayoutInflater.from(PayFeverApplication.getApplication()).inflate(R.layout.nav_drawer_row, _parent, false);
            viewHolder = new ViewHolder();
            findViews(viewHolder, _convertView);
            _convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) _convertView.getTag();
        }
        updateView(viewHolder, getItem(_position));
        return _convertView;
    }

    private class ViewHolder {
        public ImageView ivItemIcon;
        public TextView tvTitle;
    }

    private void findViews(final ViewHolder _viewHolder, final View _convertView) {
        _viewHolder.ivItemIcon = (ImageView) _convertView.findViewById(R.id.ivNavItem_NDR);
        _viewHolder.tvTitle = (TextView) _convertView.findViewById(R.id.tvTitle_NDR);
    }

    private void updateView(final ViewHolder _viewHolder, final NavDrawerItem _drawerItem) {
        _viewHolder.ivItemIcon.setImageResource(_drawerItem.getIdResourceImage());
        _viewHolder.tvTitle.setText(_drawerItem.getTitle());
    }
}
