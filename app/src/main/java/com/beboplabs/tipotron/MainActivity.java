package com.beboplabs.tipotron;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public final static String BILL_VALUE = "billValue";

    EditText checkInitialAmount;
    Button submitTotal;
    ImageView fuckThisNoise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInitialAmount = (EditText) findViewById(R.id.check_initial_amount);

        submitTotal = (Button) findViewById(R.id.submit_total);

//        fuckThisNoise = (ImageView) findViewById(R.id.background_image);
//        fuckThisNoise.setImageDrawable(getResources().getDrawable(R.drawable.cinemagraph_for_main_activity_view));
//        AnimationDrawable frameAnimation = (AnimationDrawable) fuckThisNoise.getDrawable();
//        frameAnimation.start();
    }

    public void sendToTipSelection(View view) {
        Intent intent = new Intent(this, TipSelectionActivity.class);
        String billSubTotal = checkInitialAmount.getText().toString();
        intent.putExtra(BILL_VALUE, billSubTotal);
        startActivity(intent);
    }
}
