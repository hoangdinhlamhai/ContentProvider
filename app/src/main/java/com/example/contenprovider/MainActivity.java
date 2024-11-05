package com.example.contenprovider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private Button readmessage,readcontact,readcalllog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readmessage = findViewById(R.id.btnmessage);
        readcontact = findViewById(R.id.btncontact);
        readcalllog = findViewById(R.id.btncalllog);
        addEvent();
    }
    private void addEvent(){
        readmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Message_Layout.class);
                startActivity(intent);
            }
        });
        readcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Contact_Layout.class);
                startActivity(intent);
            }
        });
        readcalllog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CallLog_Layout.class);
                startActivity(intent);
            }
        });
    }

}
