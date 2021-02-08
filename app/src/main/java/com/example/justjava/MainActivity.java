package com.example.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int priceOfCoffee =  5;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        boolean addWhippedCream = addWhippedCream();
        boolean addChocolate = addChocolate();
        int price = calculatePrice(addWhippedCream, addChocolate);
        String name = getName();
        String message = createOrderSummary(price, addWhippedCream, addChocolate, name);
        displayMessage(message);
        composeEmail(name, message);
    }

    private String createOrderSummary (int price, boolean addWhippedCream, boolean addChocolate, String name) {
        String summary = "Name: " + name +
                         "\nAdd whipped cream? " + addWhippedCream +
                         "\nAdd chocolate? " + addChocolate +
                         "\nQuantity: " + this.quantity +
                         "\nTotal: $" + price + "\nThank you!";
        return summary;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate)  {
        int addFee = 0;
        if (addWhippedCream) {
            addFee += 1;
        }
        if (addChocolate) {
            addFee += 2;
        }

        return ( priceOfCoffee + addFee ) * quantity;
    }

    private int addToppingFee(boolean addWhippedCream, boolean addChocolate) {
        int addFee = 0;
        if (addWhippedCream) {
            addFee += 1;
        }
        if (addChocolate) {
            addFee += 2;
        }

        return addFee * quantity;
    }

    public void increase (View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity += 1;
        display(quantity);
    }

    public void decrease (View view) {
        if (quantity == 1 ) {
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    private boolean addWhippedCream() {
        CheckBox whippedCreamBox = findViewById(R.id.whipped_cream_checkBox);
        boolean isChecked = whippedCreamBox.isChecked();
        return isChecked;
    }

    private boolean addChocolate() {
        CheckBox chocolateBox = findViewById(R.id.chocolate_checkBox);
        boolean isChecked = chocolateBox.isChecked();
        return isChecked;
    }

    private String getName() {
        EditText inputText =findViewById(R.id.name_form);
        String name = inputText.getText().toString();
        return name;
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private void composeEmail(String name, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for " + name );
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}