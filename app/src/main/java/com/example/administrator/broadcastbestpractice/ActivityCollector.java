package com.example.administrator.broadcastbestpractice;

import android.app.Activity;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016年12月29日 0029.
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<Activity>();

    public  static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public  static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public  static  void allFinish(){
        Iterator<Activity> it = activityList.iterator();
        while (it.hasNext())
        {
            Activity activity = it.next();
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
