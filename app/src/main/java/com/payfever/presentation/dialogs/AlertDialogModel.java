package com.payfever.presentation.dialogs;

/**
 * Created by
 * mRogach on 20.10.2015.
 */

public final class AlertDialogModel {

    private String mTitle;
    private String mMessage;
    private String mPositiveText;
    private String mNegativeText;

    public String getTitle() {
        return mTitle;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getPositiveText() {
        return mPositiveText;
    }

    public String getNegativeText() {
        return mNegativeText;
    }

    public static class Builder {

        protected AlertDialogModel alertDialogModel;

        public Builder() {
            alertDialogModel = new AlertDialogModel();
        }

        public Builder setTitle(final String _title) {
            alertDialogModel.mTitle = _title;
            return this;
        }

        public Builder setMessage(final String _message) {
            alertDialogModel.mMessage = _message;
            return this;
        }

        public Builder setNegativeText(final String _negativeText) {
            alertDialogModel.mNegativeText = _negativeText;
            return this;
        }

        public Builder setPositiveText(final String _positiveText) {
            alertDialogModel.mPositiveText = _positiveText;
            return this;
        }

        public AlertDialogModel build() {
            return alertDialogModel;
        }
    }
}
