package com.example.currencycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void convert(View view) {
        TextView amount_input = (TextView) findViewById(R.id.editTextText);
        TextView result = (TextView) findViewById(R.id.textView2);
        Spinner sp1 = (Spinner) findViewById(R.id.spinner1);
        Double amount = Double.parseDouble(amount_input.getText().toString());
        String currency = String.valueOf(sp1.getSelectedItem());

        if(currency.equals("Naira")) {
            result.setText(""+(amount/0.021));
        }
        else if(currency.equals("Euro(EUR)")) {
            result.setText(""+(amount/37.54));
        }
        else if(currency.equals("Yuan")){
            result.setText((""+(amount/4.84)));
        }
        else if(currency.equals("Won")){
            result.setText((""+(amount/0.025)));
        }
        else if(currency.equals("Baht")){
            result.setText((""+(amount/1.03)));
        }
        else{
            result.setText(""+(amount/34.29));
        }

    }

    public void clearFields(View view){
        TextView amount_input = (TextView) findViewById(R.id.editTextText);
        TextView result = (TextView) findViewById(R.id.textView2);

        amount_input.setText((""));
        result.setText("");
    }

}