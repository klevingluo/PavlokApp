package com.oscode.pavlokapp;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;

import java.util.List;

/**
 * Created by kluo on 5/16/15.
 */
public class ShockService extends IntentService {
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
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        long endTime = System.currentTimeMillis() + 100*1000;
        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(3000,0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                    try {
                        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
                        if (procInfos.get(0).processName.equals("com.android.browser")) {
                            System.out.println("browser running");
                        }
                        System.out.println("process running");
                    } catch (Exception e) {}
                }
        }
    }
}
