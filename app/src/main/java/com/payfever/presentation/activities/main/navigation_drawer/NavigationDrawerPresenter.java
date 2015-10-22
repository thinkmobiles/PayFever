package com.payfever.presentation.activities.main.navigation_drawer;

import android.os.Bundle;
import android.view.View;


import java.util.List;

/**
 * Created by
 * mRogach on 22.10.2015.
 */

public interface NavigationDrawerPresenter {

    void onItemClick(View _view, int _position);
    void setDataItems(List<NavDrawerItem> _items);
    void initialize(Bundle _savedInstanceState);
    void onPause();
    void setView(NavigationDrawerView _view);
}
