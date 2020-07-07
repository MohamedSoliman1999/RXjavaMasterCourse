package com.example.rxjavamastercourse;

import android.app.Application;
import android.content.Context;

public class RXApp extends Application {
    private static RXApp AppINSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        AppINSTANCE = this;
    }
    public synchronized static RXApp getAppINSTANCE(){
        synchronized (RXApp.class) {
            if(AppINSTANCE==null){
                AppINSTANCE=new RXApp();
            }
        }
        return AppINSTANCE;
    }
}
