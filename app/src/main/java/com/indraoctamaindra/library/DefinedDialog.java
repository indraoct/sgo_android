package com.indraoctamaindra.library;

import android.app.ProgressDialog;
import android.content.Context;

public class DefinedDialog {
    public static ProgressDialog CreateProgressDialog(Context context,
                                                      ProgressDialog dialog, String message) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }
}