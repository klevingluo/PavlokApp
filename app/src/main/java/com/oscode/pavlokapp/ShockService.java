package com.oscode.pavlokapp;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;

import java.lang.Exception;import java.lang.InterruptedException;import java.lang.Override;import java.lang.String;import java.lang.System;import java.lang.Thread;import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by kluo on 5/16/15.
 */
public class ShockService extends IntentService {

    List<String> Blacklist;

    ShockService s = this;
    Shock thread;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ShockService() {
       super("ShockService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (thread != null) {
            thread.stop();
        }
        Blacklist = new ArrayList<String>();

        StringTokenizer str = new StringTokenizer(intent.getAction(), ",");

        while(str.hasMoreTokens()) {
            Blacklist.add(str.nextToken());
        }

        thread = new Shock();
        thread.run();
    }

    private class Shock extends Thread {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000,0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    try {
                        ActivityManager activityManager = (ActivityManager) s.getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
                        String currentProcess = procInfos.get(0).processName;
                        if (Blacklist.contains(currentProcess)) {
                            Pavlok.getInstance().vibrate(150);
                            Pavlok.getInstance().shock(200);
                            Pavlok.getInstance().led(5);
                            System.out.println("app detected");
                        }
                        System.out.println(currentProcess + " is running");
                    } catch (Exception e) {}
                }
            }
        }
    }
}
