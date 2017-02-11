package com.example.sanjeev.chromecustomtabs;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class customChromeTabs extends AppCompatActivity {
    private CustomTabsClient mClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchCustomTabs();
        //createConnection();
    }

    private void launchCustomTabs()
    {
        CustomTabsIntent.Builder myBuilder = new CustomTabsIntent.Builder();
        CustomTabsIntent myIntent = myBuilder.build();
        myIntent.launchUrl(this, Uri.parse("https://www.github.com/getsanjeev"));
    }

    private CustomTabsSession getSession() {
        return mClient.newSession(new CustomTabsCallback() {
            @Override
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                super.onNavigationEvent(navigationEvent, extras);
            }
        });
    }


    private void createConnection()
    {
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                mClient = client;
                Toast.makeText(customChromeTabs.this, "Welcome i am in conn", Toast.LENGTH_SHORT).show();
                launchCustomTabs();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mClient = null;
                Toast.makeText(customChromeTabs.this, "Sorry FAILED", Toast.LENGTH_SHORT).show();
            }
        };
        String mPackageName = "com.android.chrome";
        CustomTabsClient.bindCustomTabsService(this,mPackageName,connection);
    }




}
