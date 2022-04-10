package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    CheckBox hasWhippedCream, chocolate;
    EditText customerName;
    int wipCream = 1;
    int Chocolate = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasWhippedCream = findViewById(R.id.hasWhippedCream);
        chocolate = findViewById(R.id.chocolate);
        customerName = findViewById(R.id.customerName);


    }

    public void decrement(View view) {
        if (quantity == 1) {
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    private int calculatePrice(boolean wip, boolean choco) {

        int basePrice = 5;

        if (wip) {
            basePrice += 1;
        }
        if (choco) {
            basePrice += 2;
        }
        return quantity * basePrice;

    }

    private String createOrderSummary(String name, int price, boolean wip, boolean choco) {


        String priceMessage = "Name: " + name + "\n";
        priceMessage += "Add Whipped Cream? " + wip + "\n";
        priceMessage += "chocolate? " + choco + "\n";
        priceMessage += "Quantity: " + quantity + "\n";
        priceMessage += "Total: $ " + price + "\n";
        priceMessage += "Thank you! ";

        return priceMessage;
    }

    private void displayQuantity(int i) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + i);
    }
//    private void displayMessage(String string){
//        TextView quantityTextView = findViewById(R.id.order_Summary_wrap_content);
//        quantityTextView.setText("" + string);
//    }

//    @SuppressLint("SetTextI18n")
//    private void displayPrice(String i) {
//        TextView priceTextView = findViewById(R.id.price_text_view);
//        priceTextView.setText( NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(i));
//    }


    public void submitOrder(View view) {
        String name = customerName.getText().toString();
        Boolean wip = hasWhippedCream.isChecked();
        Boolean choco = chocolate.isChecked();
        int price = calculatePrice(wip, choco);
        String priceMessage = createOrderSummary(name, price, wip, choco);


        String addresses = "prajapatiomkar01@gmail.com";
        String subject = "Just Java order for ";

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        displayMessage(priceMessage);
    }
}