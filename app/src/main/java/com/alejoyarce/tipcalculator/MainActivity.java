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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    private EditText inputBill;
    @Bind(R.id.btnCalculate)
    private Button btnCalculate;
    @Bind(R.id.inputPercentage)
    private EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    private Button btnIncrease;
    @Bind(R.id.btnDecrease)
    private Button btnDecrease;
    @Bind(R.id.btnClear)
    private Button btnClear;
    @Bind(R.id.textTip)
    private EditText textTip;

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
        hideKeyBoard();

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
}
