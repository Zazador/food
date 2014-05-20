package com.example.food.app;

/**
 * Created by zach on 5/20/14.
 */

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "JrZucBhi9hfW4FbpxvdsgILXhr3fi3ygv5twEB9w", "s39eDV2qnAUik2GDLCLUS21RMshXzemPziMrboQM");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }


}
