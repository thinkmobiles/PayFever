package com.payfever.presentation.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.payfever.R;

/**
 * Created by
 * mRogach on 20.10.2015.
 */

public abstract class AlertDialogManager {

    public static void showAlertDialog(final Context _context, final AlertDialogModel _model, final OneButtonDialogListener _listener) {
        AlertDialog.Builder builder = getAlertDialog(_context, _model, _listener);
        showDialog(builder, _context);
    }

    public static void showAlertDialog(final Context _context, final AlertDialogModel _model, final TwoButtonDialogListener _listener) {
        AlertDialog.Builder builder = getAlertDialog(_context, _model, _listener);
        builder.setNegativeButton(_model.getNegativeText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _listener.secondButtonClick();
            }
        });
        showDialog(builder, _context);
    }

    private static AlertDialog.Builder getAlertDialog(final Context _context, final AlertDialogModel _model, final OneButtonDialogListener _listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(_context, R.style.AlertDialogStyle);
        builder.setTitle(_model.getTitle());
        builder.setMessage(_model.getMessage());
        builder.setPositiveButton(_model.getPositiveText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _listener.oneButtonClick();
            }
        });
        return builder;
    }

    private static ColorStateList getColorStateList(final Context _context, final int _colorId) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            return _context.getResources().getColorStateList(_colorId, _context.getTheme());
        } else {
            return _context.getResources().getColorStateList(_colorId);
        }
    }

    private static int getColor(final Context _context, final int _colorId) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            return _context.getResources().getColor(_colorId, _context.getTheme());
        } else {
            return _context.getResources().getColor(_colorId);
        }
    }

    private static void setButtonBackgroundColor(final Context _context, final AlertDialog _dialog) {
        _dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setBackgroundColor(getColor(_context, android.R.color.transparent));
                positiveButton.setTextColor(getColorStateList(_context, R.color.alertdialog_text_selector));
                Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                if (negativeButton != null) {
                    negativeButton.setBackgroundColor(getColor(_context, android.R.color.transparent));
                    negativeButton.setTextColor(getColorStateList(_context, R.color.alertdialog_text_selector));
                }
            }
        });
    }

    private static void showDialog(final AlertDialog.Builder _builder, final Context _context) {
        AlertDialog dialog = _builder.create();
        setButtonBackgroundColor(_context, dialog);
        dialog.show();
    }

}
