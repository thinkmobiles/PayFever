package com.payfever.presentation.activities.main.navigation_drawer;

import android.os.Bundle;
import android.view.View;

import com.payfever.R;
import com.payfever.presentation.PayFeverApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * mRogach on 22.10.2015.
 */

public final class NavigationDrawerPresenterImpl implements NavigationDrawerPresenter {

    private NavigationDrawerView navigationDrawerView;

    @Override
    public void onItemClick(View _view, int _position) {
        navigationDrawerView.onItemClick(_view, _position);
    }

    @Override
    public void setDataItems(List<NavDrawerItem> _items) {
        navigationDrawerView.setDataItems(_items);
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(NavigationDrawerView _view) {
        navigationDrawerView = _view;
        navigationDrawerView.setDataItems(getMenuList());
    }

    private List<NavDrawerItem> getMenuList() {
        List<NavDrawerItem> list = new ArrayList<>();
        list.add(new NavDrawerItem.Builder()
                .setIdResourceImage(R.drawable.ic_avatar_contact)
                .setTitle(PayFeverApplication.getApplication().getResources().getString(R.string.drawer_item_my_network)).build());
        list.add(new NavDrawerItem.Builder()
                .setIdResourceImage(R.drawable.ic_avatar_contact)
                .setTitle(PayFeverApplication.getApplication().getResources().getString(R.string.drawer_item_balance)).build());
        list.add(new NavDrawerItem.Builder()
                .setIdResourceImage(R.drawable.ic_avatar_contact)
                .setTitle(PayFeverApplication.getApplication().getResources().getString(R.string.drawer_item_pay_chat)).build());
        list.add(new NavDrawerItem.Builder()
                .setIdResourceImage(R.drawable.ic_avatar_contact)
                .setTitle(PayFeverApplication.getApplication().getResources().getString(R.string.drawer_item_about)).build());
        list.add(new NavDrawerItem.Builder()
                .setIdResourceImage(R.drawable.ic_avatar_contact)
                .setTitle(PayFeverApplication.getApplication().getResources().getString(R.string.drawer_item_pay_tone)).build());
        return list;
    }
}
