package com.example.dmiranda.caremetoo.config;

import android.os.Environment;

/**
 * Created by dmiranda on 10/30/14.
 */
public class CareMeTooConfig {

    public static String externalPath = Environment.getExternalStorageDirectory().getPath() + "/CareMeToo";
    public static String externalVoiceLogsPath = externalPath + "/voice";


}