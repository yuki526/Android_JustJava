package com.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        String message = createOrderSummary(price);
        displayMessage(message);
    }

    private String createOrderSummary (int price) {
        String summary = "Name: Kaptain Kunal \nQuantity: " + this.quantity +
                         "\nTotal: $" + price + "\nThank you!";
        return summary;
    }

    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    public void increase (View view) {
        quantity += 1;
        display(quantity);
    }

    public void decrease (View view) {
        if (quantity != 0 ) {
         quantity -= 1;
        }
        display(quantity);
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}