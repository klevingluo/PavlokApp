package com.oscode.pavlokapp;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

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
        for (int i=0; i<35; i+=3) {
            String appname = getApp(i);
            int ind = appname.indexOf('/');
            String processName = appname.substring(21,ind);
            System.out.println(processName);
        }

        long endTime = System.currentTimeMillis() + 100*1000;
        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(5000,0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                    try {
                        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
                        String currentProcess = procInfos.get(0).processName;
                        if (currentProcess.equals("com.android.browser")) {

                        }
                    } catch (Exception e) {}
                }
        }
    }

    public String getApp(int i){
        PackageManager pm=getPackageManager();
        Intent main=new Intent(Intent.ACTION_MAIN, null);

        main.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> launchables=pm.queryIntentActivities(main, 0);
        return launchables.get(i).toString();
    }
}
