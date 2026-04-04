package com.example.currency_converter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Spinner fromCurrency = findViewById(R.id.fromCurrency);
        Spinner toCurrency = findViewById(R.id.toCurrency);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.currencies,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);
        EditText amountInput = findViewById(R.id.amountInput);
        Button convertBtn = findViewById(R.id.convertBtn);
        TextView resultText = findViewById(R.id.resultText);

        convertBtn.setOnClickListener(v -> {

            String amountStr = amountInput.getText().toString();

            if(amountStr.isEmpty()){
                resultText.setText("Enter amount");
                return;
            }

            double amount = Double.parseDouble(amountStr);

            String from = fromCurrency.getSelectedItem().toString();
            String to = toCurrency.getSelectedItem().toString();

            double amountInINR = 0;

            if(from.equals("INR")) amountInINR = amount;
            else if(from.equals("USD")) amountInINR = amount * 83;
            else if(from.equals("EUR")) amountInINR = amount * 90;
            else if(from.equals("JPY")) amountInINR = amount * 0.55;

            double finalAmount = 0;

            if(to.equals("INR")) finalAmount = amountInINR;
            else if(to.equals("USD")) finalAmount = amountInINR / 83;
            else if(to.equals("EUR")) finalAmount = amountInINR / 90;
            else if(to.equals("JPY")) finalAmount = amountInINR / 0.55;

            resultText.setText("Result: " + finalAmount + " " + to);
        });
        Button settingsBtn = findViewById(R.id.settingsBtn);

        settingsBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    }
