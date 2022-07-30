package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;

import com.example.aidlservice.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface myAidlInterface = null;
    private TextView txt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                try {
                    String s = myAidlInterface.getStringFromService();
                    txt.setText(s);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        Intent intent = new Intent();
        intent.setAction("com.example.service.action");
        intent.setPackage("com.example.aidlservice");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }
}