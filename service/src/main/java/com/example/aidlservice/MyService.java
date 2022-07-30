package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "---MyService---";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new Mybinder();
    }

    class Mybinder extends IMyAidlInterface.Stub{
        @Override
        public String getStringFromService() throws RemoteException {
            Log.d(TAG, "service 端的方法被client调用了");
            return "777";
        }
    }
}