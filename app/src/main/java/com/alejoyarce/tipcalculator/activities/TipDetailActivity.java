package com.alejoyarce.tipcalculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alejoyarce.tipcalculator.R;
import com.alejoyarce.tipcalculator.TipCalcApp;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipDetailActivity extends AppCompatActivity {

    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimeStamp)
    TextView txtTimeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String strTotal = String.format(getString(R.string.tip_detail_message_bill), intent.getDoubleExtra(TipCalcApp.BILL_TOTAL_KEY, 0d ));
        String strTip = String.format(getString(R.string.global_message_tip), intent.getDoubleExtra(TipCalcApp.TIP_KEY, 0d ));

        txtBillTotal.setText(strTotal);
        txtTip.setText(strTip);
        txtTimeStamp.setText(intent.getStringExtra(TipCalcApp.DATE_KEY));
    }
}
