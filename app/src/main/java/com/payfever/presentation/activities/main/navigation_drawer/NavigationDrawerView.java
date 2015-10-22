package com.payfever.presentation.activities.main.navigation_drawer;

import android.view.View;

import java.util.List;

/**
 * Created by
 * mRogach on 22.10.2015.
 */

public interface NavigationDrawerView {

    void onItemClick(View _view, int _position);
    void setDataItems(List<NavDrawerItem> _items);
}
