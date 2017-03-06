package com.github.mobile.aspects.Lazy;

import android.util.Log;

/**
 * Created by henrikmnm on 06/03/2017.
 */

public class LazyLogger {

    public LazyLogger(){}

    public static void logLazyness(String tag, String message){
        Log.d(tag, message);
    }

}
