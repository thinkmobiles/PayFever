package com.payfever.presentation.activities.main.navigation_drawer;

/**
 * Created by
 * mRogach on 22.10.2015.
 */

public final class NavDrawerItem {

    private int idResourceImage;
    private String mTitle;

    public int getIdResourceImage() {
        return idResourceImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public static class Builder {

        protected NavDrawerItem navDrawerItem;

        public Builder() {
            navDrawerItem = new NavDrawerItem();
        }

        public Builder setIdResourceImage(final int _idResource) {
            navDrawerItem.idResourceImage = _idResource;
            return this;
        }

        public Builder setTitle(final String _title) {
            navDrawerItem.mTitle = _title;
            return this;
        }

        public NavDrawerItem build() {
            return navDrawerItem;
        }
    }
}
