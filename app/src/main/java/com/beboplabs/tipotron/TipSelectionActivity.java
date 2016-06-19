package com.beboplabs.tipotron;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import static java.lang.Double.parseDouble;

public class TipSelectionActivity extends Activity {

    Button tip0, tip10, tip15, tip18, tip20, tip25;
    String billValue;
    TextView howWasService, finalTotalWithTip, tipAmount, tipAmountText, totalAmountAfterTip;
    Animation expandIn;
    AlertDialog zeroTipConfirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_selection);

        tip0 = (Button) findViewById(R.id.zero_tip);
        tip10 = (Button) findViewById(R.id.ten_tip);
        tip15 = (Button) findViewById(R.id.fifteen_tip);
        tip18 = (Button) findViewById(R.id.eighteen_tip);
        tip20 = (Button) findViewById(R.id.twenty_tip);
        tip25 = (Button) findViewById(R.id.twenty_five_tip);

        howWasService = (TextView) findViewById(R.id.how_was_service);
        finalTotalWithTip = (TextView) findViewById(R.id.total_amount_with_tip);
        tipAmount = (TextView) findViewById(R.id.tip_amount);
        tipAmountText = (TextView) findViewById(R.id.tip_amount_text);
        totalAmountAfterTip = (TextView) findViewById(R.id.check_total_amount);

        Intent intent = getIntent();
        billValue = intent.getStringExtra(MainActivity.BILL_VALUE);

        expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);

        tip0.startAnimation(expandIn);
        tip10.startAnimation(expandIn);
        tip15.startAnimation(expandIn);
        tip18.startAnimation(expandIn);
        tip20.startAnimation(expandIn);
        tip25.startAnimation(expandIn);

        tip0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleZeroTipDialog(TipSelectionActivity.this);
            }
        });

        tip10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTipMath(".10");
            }
        });

        tip15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTipMath(".15");
            }
        });

        tip18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTipMath(".18");
            }
        });

        tip20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTipMath(".20");
            }
        });

        tip25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTipMath(".25");
            }
        });

        tipAmount.setVisibility(View.INVISIBLE);
        tipAmountText.setVisibility(View.INVISIBLE);
        totalAmountAfterTip.setVisibility(View.INVISIBLE);
        finalTotalWithTip.setVisibility(View.INVISIBLE);

        // Until I can figure out why the SystemUI bar is colored white all the time, just hide it.
        Window window = TipSelectionActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(TipSelectionActivity.this.getResources().getColor(R.color.colorPrimaryDark));
    }

    public void handleZeroTipDialog(Context context) {
        AlertDialog.Builder zeroTipDialog = new AlertDialog.Builder(context);
        zeroTipConfirmDialog = zeroTipDialog.setTitle(R.string.zero_tip_title)
                .setMessage(R.string.zero_tip_text)
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTipMath("0");
                    }
                })
                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


    public void doTipMath(String whichButtonWasPressed) {
        String value = billValue;
        Double aDouble = parseDouble(value);
        double finalBillValue = aDouble * (1 + parseDouble(whichButtonWasPressed));
        String myString = NumberFormat.getCurrencyInstance().format(finalBillValue);
        String myTip = NumberFormat.getCurrencyInstance().format(parseDouble(billValue) * parseDouble(whichButtonWasPressed));
        tipAmount.setText(myTip);
        finalTotalWithTip.setText(myString);
        makeTextFieldsVisible();
    }

    public void makeTextFieldsVisible() {
        tipAmount.startAnimation(expandIn);
        tipAmountText.startAnimation(expandIn);
        totalAmountAfterTip.startAnimation(expandIn);
        finalTotalWithTip.startAnimation(expandIn);
        tipAmount.setVisibility(View.VISIBLE);
        tipAmountText.setVisibility(View.VISIBLE);
        totalAmountAfterTip.setVisibility(View.VISIBLE);
        finalTotalWithTip.setVisibility(View.VISIBLE);
    }
}
