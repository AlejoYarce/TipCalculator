package com.alejoyarce.tipcalculator;

import android.app.Application;

public class TipCalcApp extends Application {

    public final static String ABOUT_URL = "https://github.com/AlejoYarce/TipCalculator";
    public final static int TIP_STEP_CHANGE = 1;
    public final static int DEFAULT_TIP_PERCENTAGE = 10;
    public final static String TIP_KEY = "tip";
    public final static String DATE_KEY = "timeStamp";
    public final static String BILL_TOTAL_KEY = "total";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
