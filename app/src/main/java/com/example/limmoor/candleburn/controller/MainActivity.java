package com.example.limmoor.candleburn.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.limmoor.candleburn.R;
import com.example.limmoor.candleburn.controller.ViewBuilder;
import com.example.limmoor.candleburn.model.candle.CandleCalculator;
import com.example.limmoor.candleburn.model.candle.Candle;
import com.example.limmoor.candleburn.model.candle.Material;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.durationResult).setFocusable(false);

        ViewBuilder viewBuilder = new ViewBuilder();
        viewBuilder.buildSpinner(this);

        initButtons();
    }

    private void initButtons(){
        Button calcBtn = findViewById(R.id.calcBtn);
        calcBtn.setOnClickListener(this::onClickCalculate);

        Button resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(this::onClickReset);

    }

    private void onClickCalculate(View view) {
        CandleCalculator candleCalculator = new CandleCalculator(getCandleDataInput());
        ((TextView) findViewById(R.id.durationResult)).setText(candleCalculator.getTimeLeft());
    }

    private void onClickReset(View view){
        ((EditText)findViewById(R.id.lengthInput)).getText().clear();
        ((EditText)findViewById(R.id.diameterInput)).getText().clear();
        ((EditText)findViewById(R.id.durationResult)).getText().clear();
    }

    private Candle getCandleDataInput() {
        Spinner spinner = findViewById(R.id.candleSpinner);
        Candle selectedItem = new Candle();
        selectedItem.setMaterial((Material) spinner.getSelectedItem());
        selectedItem.setLength(getInputFieldContent(R.id.lengthInput));
        selectedItem.setDiameter(getInputFieldContent(R.id.diameterInput));
        return selectedItem;
    }

    private double getInputFieldContent(int inputFieldId) {
        try {
            String number = ((EditText) findViewById(inputFieldId)).getText().toString();
            return Double.parseDouble(number);
        } catch (NumberFormatException ex) {
            Log.e("FEHLER", "User zu blöd ne zahl einzugeben");
        }
        return 0;
    }
}
