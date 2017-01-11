package com.swarawan.countrycode.view.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.swarawan.countrycode.utils.PrefUtils;
import com.swarawan.countrycode.view.login.LoginActivity;
import com.swarawan.countrycode.view.main.MainActivity;

/**
 * Created by rioswarawan on 1/11/17.
 */

public class LauncherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, PrefUtils.isSignedIn(this) ? MainActivity.class : LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
}
