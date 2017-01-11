package com.swarawan.countrycode.view.login;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.resepkita.utils.DialogUtils;
import com.resepkita.utils.ViewUtils;
import com.swarawan.countrycode.R;
import com.swarawan.countrycode.utils.AuthUtils;

public class LoginActivity extends FragmentActivity {

    private AuthUtils mAuthUtils;
    private View ourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        ourView = getWindow().getDecorView();
        ViewUtils.setOnClick(ourView, R.id.signInButton, onSignInClicked);
    }

    private void initialize() {
        mAuthUtils = new AuthUtils(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuthUtils.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuthUtils.onStop();
    }

    private View.OnClickListener onSignInClicked = view -> {
        mAuthUtils.showUsers();
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AuthUtils.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                mAuthUtils.signInGoogle(account);
            } else {
                Toast.makeText(this, getString(R.string.message_auth_failed), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
