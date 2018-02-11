package com.cryptoconverter.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button click;
    public static EditText convertedAmountTextBox;
    public static EditText inputAmountTextBox;
    public static Spinner selectCryptoSpinner;
    public static TextView fetchedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsontest);
        click = findViewById(R.id.button);
        fetchedData = findViewById(R.id.fetcheddata);

        click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FetchData process = new FetchData();
                process.execute();
            }
        });

        //Used for main activity
        /*

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = findViewById(R.id.button);
        inputAmountTextBox = findViewById(R.id.inputAmountTextBox);
        convertedAmountTextBox = findViewById(R.id.convertedAmountTextBox);
        selectCryptoSpinner = findViewById(R.id.selectCryptoSpinner);

        click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FetchData process = new FetchData();
                process.execute();
            }
        });

        */
    }

    public static String getSelectedCryptoVal() {
        String selectedCrypto = selectCryptoSpinner.getSelectedItem().toString();
        switch (selectedCrypto) {
            case "Bitcoin (BTC)": return "BTC";
            case "Litecoin (LTC)": return "LTC";
            case "Ethereum (ETH)": return  "ETH";
            case "Monero (XMR)": return "XMR";
            default: return "Error";
        }
    }
}
