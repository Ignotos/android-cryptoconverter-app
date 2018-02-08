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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button click;
    public static EditText convertedAmountTextBox;
    public static EditText inputAmountTextBox;
    public static AlertDialog.Builder builder;
    //public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //context = getApplicationContext();
        setContentView(R.layout.activity_main);

        click = findViewById(R.id.button);
        inputAmountTextBox = findViewById(R.id.inputAmountTextBox);
        convertedAmountTextBox = findViewById(R.id.convertedAmountTextBox);

        click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FetchData process = new FetchData();
                process.execute();
            }
        });
    }
}
