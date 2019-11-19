package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * Edited to be reviewed
 * free space to review comments
 *
 *
 *
 * @author Group 1
 */

public class MainActivity extends AppCompatActivity {
    final int SEND_SMS_PERMISSION_REQUEST_CODE=1;
    EditText message;
    EditText number;
    Button send;
    /**
     * sms test module
     *
     * @param user new network user
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.inputnumber);
        message = findViewById(R.id.inputmessage);
        send = findViewById(R.id.sendbutton);

        send.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            send.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }

    }
    /**
     *  sends the sms to the reciver
     *
     * @param  view messaggio da inviare
     */
    public void onSend(View V){
        String phonenumber = number.getText().toString();
        String smsmessage = message.getText().toString();

        if(phonenumber == null || smsmessage == null || phonenumber.length()==0 || smsmessage.length()==0){
            return;
        }
        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber,null,smsmessage,null,null);
            Toast.makeText(this,"message sent",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"permissions denied",Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * checks if permissions granted
     *
     * @param permission
     * @return true if android sms permissions granted
     */
    public boolean checkPermission (String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}



