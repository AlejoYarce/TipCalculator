package com.alejoyarce.tipcalculator;

import android.app.Application;

public class TipCalcApp extends Application {

    private final static String ABOUT_URL = "https://github.com/AlejoYarce/TipCalculator";
    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
