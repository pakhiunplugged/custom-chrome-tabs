package com.example.sanjeev.chromecustomtabs;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        createConnection();
        launchCustomTabs();
    }
    //Only this function is responsible for launching of tab event and display website content
    private void launchCustomTabs()
    {
        CustomTabsIntent.Builder myBuilder = new CustomTabsIntent.Builder();
        myBuilder.setShowTitle(true);
        myBuilder.addDefaultShareMenuItem();
        myBuilder.setToolbarColor(getResources().getColor(R.color.colorAccent));
        myBuilder.addMenuItem("Add me",null);
        CustomTabsIntent myIntent = myBuilder.build();
        myIntent.launchUrl(this, Uri.parse("https://www.github.com/getsanjeev"));
    }

    // This function is called to optimize the event, causes the browser to be ready for launching URL
    private void createConnection()
    {
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                mClient = client;
                mClient.warmup(0L);
                CustomTabsSession mTabSession = mClient.newSession(null);
                mTabSession.mayLaunchUrl(Uri.parse("https://github.com/getsanjeev"),null,null);
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
