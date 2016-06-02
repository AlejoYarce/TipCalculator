package com.alejoyarce.tipcalculator;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnCalculate)
    Button btnCalculate;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.textTip)
    TextView textTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
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
            double tip = total * (tipPercentage / 100d);

            String formattedTip = String.format(getString(R.string.global_message_tip), tip);
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
