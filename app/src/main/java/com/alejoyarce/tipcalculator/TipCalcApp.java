package com.alejoyarce.tipcalculator;

import android.app.Application;

public class TipCalcApp extends Application {

    private final static String ABOUT_URL = "https://github.com/AlejoYarce/TipCalculator";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
