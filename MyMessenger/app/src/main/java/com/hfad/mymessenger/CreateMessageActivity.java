package com.hfad.mymessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateMessageActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_message);
    }
    //Call onSendMessage() when the button is clicked
    public void onSendMessage(View view) {
    }
    public class ReceiveMessageActivity extends Activity {
        public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        public void onSendMessage(View view) {
            EditText messageView = (EditText)findViewById(R.id.message); String
                    messageText = messageView.getText().toString();
            Intent intent = new Intent(this, ReceiveMessageActivity.class);
            intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, messageText);
            startActivity(intent);
        }
    }
}