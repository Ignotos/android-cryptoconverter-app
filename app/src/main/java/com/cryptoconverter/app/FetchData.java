package com.cryptoconverter.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by mignoto on 04/02/18.
 */

public class FetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String btc_cad_string = "";
    String selectedCrypto = "";
    JSONObject returnedJson;
    JSONArray quotes;
    String testStringToDisplay = "";

    protected Void doInBackground(Void... voids) {
        selectedCrypto = MainActivity.getSelectedCryptoVal();
        try {
            URL url = new URL("https://coinsquare.io/?method=quotes");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            returnedJson = new JSONObject(data);
            quotes = returnedJson.getJSONArray("quotes");
            for (int i = 0; i < quotes.length(); i++) {
                JSONObject quote = quotes.getJSONObject(i);
                String ticker = quote.getString("ticker");
                if (ticker == selectedCrypto) {
                    testStringToDisplay = quote.toString();
                    break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        try {
            selectedCrypto = MainActivity.getSelectedCryptoVal();
            //https://coinsquare.io/?method=quotes
            URL url = new URL("https://coinsquare.io/?method=book&ticker=CAD&base=" + selectedCrypto);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("book");
            JSONObject firstJsonObject = jsonArray.getJSONObject(0);
            double prc = firstJsonObject.getDouble("prc");
            double cad_ubtc = 0.000001 * prc / 100;
            //double btc_cad = (1/cad_ubtc);
            //btc_cad_string = "" + btc_cad;
            DecimalFormat formatter = new DecimalFormat("#0.00000000");
            btc_cad_string = formatter.format(cad_ubtc);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.fetchedData.setText(testStringToDisplay);
        /*
        super.onPostExecute(aVoid);
        double btc_cad_conversion = Double.parseDouble(btc_cad_string);
        String dollarAmountInputString = MainActivity.inputAmountTextBox.getText().toString();
        try {
            double dollarAmountInput = Double.parseDouble(dollarAmountInputString);
            double convertedAmount = btc_cad_conversion * dollarAmountInput;
            DecimalFormat formatter = new DecimalFormat("#0.00000000");
            btc_cad_string = formatter.format(convertedAmount);
            MainActivity.convertedAmountTextBox.setText(btc_cad_string + " " + selectedCrypto);
        }
        catch (NumberFormatException e) {
            MainActivity.convertedAmountTextBox.setText("Error! input transaction amount must be a decimal number.");
        }
        */
    }
}
