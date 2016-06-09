package com.alejoyarce.tipcalculator.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.alejoyarce.tipcalculator.R;
import com.alejoyarce.tipcalculator.TipCalcApp;
import com.alejoyarce.tipcalculator.domain.TipRecord;
import com.alejoyarce.tipcalculator.fragments.TipHistoryListFragment;
import com.alejoyarce.tipcalculator.fragments.TipHistoryListFragmentListener;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.textTip)
    TextView textTip;

    private TipHistoryListFragmentListener tipHistoryListFragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentHistoryList);
        fragment.setRetainInstance(true);
        tipHistoryListFragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.action_about ) {
             about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnCalculate)
    public void calculateTip() {
        String bill = inputBill.getText().toString();
        if ( !bill.isEmpty() ) {
            double total = Double.parseDouble(bill);
            int tipPercentage = (inputPercentage.getText() != null && !inputPercentage.getText().toString().isEmpty())
                    ? Integer.valueOf(inputPercentage.getText().toString()) : TipCalcApp.DEFAULT_TIP_PERCENTAGE;

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimeStamp(new Date());

            String formattedTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());
            tipHistoryListFragmentListener.addRecord(tipRecord);

            textTip.setVisibility(View.VISIBLE);
            textTip.setText(formattedTip);

            if ( tipPercentage == TipCalcApp.DEFAULT_TIP_PERCENTAGE ) {
                inputPercentage.setText(String.valueOf(TipCalcApp.DEFAULT_TIP_PERCENTAGE));
            }
        }

        hideKeyBoard();
    }

    @OnClick(R.id.btnIncrease)
    public void increaseTip() {
        hideKeyBoard();
        changeTip(TipCalcApp.TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void decreaseTip() {
        hideKeyBoard();
        changeTip(-TipCalcApp.TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public void clearRecords() {
        hideKeyBoard();
        tipHistoryListFragmentListener.clearRecords();
    }

    private void hideKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        try {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), inputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName() , Log.getStackTraceString(npe));
        }
    }

    private void about() {
        TipCalcApp tipCalcApp = (TipCalcApp)getApplication();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(tipCalcApp.getAboutUrl()));
        startActivity(intent);
    }

    private void changeTip(int tipStepChange) {
        int tipPercentage = (inputPercentage.getText() != null && !inputPercentage.getText().toString().isEmpty())
                ? Integer.valueOf(inputPercentage.getText().toString()) : TipCalcApp.DEFAULT_TIP_PERCENTAGE;
        tipPercentage += tipStepChange;

        if ( tipPercentage == 0 ) {
            inputPercentage.setText(String.valueOf(0));
        } else if ( tipPercentage > 100 ) {
            inputPercentage.setText(String.valueOf(100));
        } else if ( tipPercentage > 0 ) {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }

        calculateTip();
    }
}
