package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    private void display(int i) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + i);
    }

    @SuppressLint("SetTextI18n")
    private void displayPrice(int i) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(i) + "\nThank you!");
    }


    public void submitOrder(View view) {
        displayPrice(quantity * price);
    }
}