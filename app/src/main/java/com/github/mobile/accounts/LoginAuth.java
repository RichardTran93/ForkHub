package com.github.mobile.accounts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.eclipse.egit.github.core.User;

import roboguice.util.RoboAsyncTask;

/**
 * Created by EpiK on 2/6/2017.
 */
public abstract class LoginAuth extends RoboAsyncTask<User> {

    AlertDialog dialog;

    public LoginAuth(Context context, AlertDialog dialog) {
        super(context);
        this.dialog = dialog;
    }
    @Override
    protected void onException(Exception e) throws RuntimeException {
        dialog.dismiss();
    }

    @Override
    public void onSuccess(User user) {
        dialog.dismiss();
    }
}
