package com.payfever.presentation.activities.main.navigation_drawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.payfever.R;
import com.payfever.presentation.basics.BaseFragment;

import java.util.List;

/**
 * Created by
 * mRogach on 22.10.2015.
 */

public final class FragmentDrawerMenu extends BaseFragment
        implements AdapterView.OnItemClickListener, NavigationDrawerView {

    private ListView mListView;
    private NavigationDrawerAdapter mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentDrawerListener drawerListener;
    private View containerView;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerPresenter mPresenter;

    public void setDrawerListener(final FragmentDrawerListener _listener) {
        this.drawerListener = _listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_navigation_drawer);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
        findUI();
        initListeners();
        mAdapter = new NavigationDrawerAdapter();
        mPresenter.initialize(savedInstanceState);
        mPresenter.setView(this);
    }

    private void findUI() {
        mListView = $(R.id.lvDrawerList_FND);
    }

    private void initListeners() {
        mListView.setOnItemClickListener(this);
    }

    private void initPresenter() {
        mPresenter = new NavigationDrawerPresenterImpl();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.onItemClick(view, position);
    }

    public void setUp(final Activity activity, int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = activity.findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mActivity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mActivity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        drawerListener.onDrawerItemSelected(view, position);
        mDrawerLayout.closeDrawer(containerView);
    }

    @Override
    public void setDataItems(List<NavDrawerItem> _items) {
        mAdapter.setMenuItems(_items);
        mListView.setAdapter(mAdapter);
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }
}
