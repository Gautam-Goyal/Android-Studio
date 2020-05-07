/**
        * IMPORTANT: Make sure you are using the correct package name.
        * This example uses the package name:
        * package com.example.android.justjava
        * If you get an error when copying this code into Android studio, update it to match teh package name found
        * in the project's AndroidManifest.xml file.
        **/

        package com.example.javaup;



import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View layoutW;
        final View[] layoutUW = new View[1];

        final MediaPlayer whipSound=MediaPlayer.create(this,R.raw.whipspray);

        final CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whip_check);
        final boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        final CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check);
        boolean hasChocolate =chocolateCheckBox.isChecked();

        WhippedCreamCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
                Snackbar whipSnack=Snackbar.make(buttonView,getString(R.string.whipcreamadd),Snackbar.LENGTH_SHORT);
                whipSnack.setActionTextColor(getResources().getColor(R.color.undo)).setAction(getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(WhippedCreamCheckBox.isChecked())
                            WhippedCreamCheckBox.toggle();
                    }
                });
                if(WhippedCreamCheckBox.isChecked())
                {View v=whipSnack.getView();
                    v.setBackgroundColor(getResources().getColor(R.color.SnackbarBack));
                    whipSnack.show();
                whipSound.start();}

                else{
                    Snackbar undoShipSnack = Snackbar.make(buttonView, getString(R.string.whipcreamr), Snackbar.LENGTH_SHORT);
                    View v=undoShipSnack.getView();
                    v.setBackgroundColor(getResources().getColor(R.color.SnackbarBack));
                    undoShipSnack.show();
                }

            }
        });

        chocolateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
                Snackbar chocSnack=Snackbar.make(buttonView,getString(R.string.chocadd),Snackbar.LENGTH_SHORT);
                chocSnack.setActionTextColor(getResources().getColor(R.color.undo)).setAction(getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(chocolateCheckBox.isChecked())
                            chocolateCheckBox.toggle();
                    }
                });
                if(chocolateCheckBox.isChecked()){
                    View v=chocSnack.getView();
                    v.setBackgroundColor(getResources().getColor(R.color.SnackbarBack));
                    chocSnack.show();
                whipSound.start();}
                else{
                    Snackbar undoChocSnack = Snackbar.make(buttonView, getString(R.string.chocre), Snackbar.LENGTH_SHORT);
                    View v=undoChocSnack.getView();
                    v.setBackgroundColor(getResources().getColor(R.color.SnackbarBack));
                    undoChocSnack.show();
                }

            }
        });
    }

    /**
     * This method is called when the order button is clicked.
     */
    public int checktopping(int extra,boolean hasWhippedCream,boolean hasChocolate)
    {
        if(hasWhippedCream && hasChocolate)
        extra=3;
        else if(hasChocolate)
            extra=2;
        else
            extra=1;
        return extra;
    }

    public void submitOrder(View view) {
        String message;
        int extra=0;
        EditText nameEnter =(EditText) findViewById(R.id.name_enter);
        String Namedisplay =nameEnter.getText().toString();

        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whip_check);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check);
        boolean hasChocolate =chocolateCheckBox.isChecked();

        extra=checktopping(extra,hasWhippedCream,hasChocolate);
        int price=calculatePrice(extra);

        message=createordersummary(price,hasWhippedCream,hasChocolate,Namedisplay);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + Namedisplay);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(message);
    }

    private String createordersummary(int price,boolean whippedcream ,boolean chocolate,String name)
    {
        String summary;
        summary=getString(R.string.name) + ":" + name + "\n" + getString(R.string.quantity) + ":" + quantity + "\n" + getString(R.string.addwhip) + "?" + whippedcream + "\n" + getString(R.string.addchoc) + "?" + chocolate +  "\n" +
    getString(R.string.price) + ":$" + price + ".0" + "\n" + getString(R.string.thank);
        return summary;
    }

    public void increment(View view) {
        quantity+=1;
        display(quantity);
    }

    public void decrement(View view) {

        if(quantity==1){
            Toast.makeText(this,"You cannot have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;}
        quantity=quantity-1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));//or we can use ("" + number);
    }



    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.price_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(int extra) {
        int price = quantity * (5 + extra);
        return price;
    }
}