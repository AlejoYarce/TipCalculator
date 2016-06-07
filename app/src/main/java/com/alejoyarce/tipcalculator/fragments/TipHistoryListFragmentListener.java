package com.alejoyarce.tipcalculator.fragments;

import com.alejoyarce.tipcalculator.domain.TipRecord;

public interface TipHistoryListFragmentListener {

    void addRecord(TipRecord tipRecord);

    void clearRecords();
}
