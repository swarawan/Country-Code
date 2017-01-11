package com.swarawan.countrycode.utils;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.swarawan.countrycode.R;
import com.swarawan.countrycode.view.main.MainActivity;

import timber.log.Timber;

/**
 * Created by rioswarawan on 12/29/16.
 */

public class AuthUtils {

    private static final String TAG = "AuthUtils";
    public static final int RC_SIGN_IN = 9001;


    private FragmentActivity mActivity;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions mGoogleSignInOptions;
    private OnSignOutListener mSignOutListener;

    public AuthUtils(FragmentActivity mActivity) {
        this.mActivity = mActivity;
        this.mAuth = FirebaseAuth.getInstance();

        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(mActivity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .enableAutoManage(mActivity, onConnectionFailedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, mGoogleSignInOptions)
                .build();
    }

    public void setListener(OnSignOutListener mSignOutListener) {
        this.mSignOutListener = mSignOutListener;
    }

    public void onStart() {
        mAuth.addAuthStateListener(authStateListener);
    }

    public void onStop() {
        if (authStateListener != null)
            mAuth.removeAuthStateListener(authStateListener);
    }

    public void showUsers() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signInGoogle(GoogleSignInAccount account) {
        Timber.d(mActivity.getString(R.string.log_google_id, account.getId()));
        Timber.d(mActivity.getString(R.string.log_google_token, account.getIdToken()));

        PrefUtils.storeGoogleToken(mActivity, account.getIdToken());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(mActivity, onCompleteListener);
    }

    public void signOutGoogle() {
        mAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(signOutCallback);
    }

    public void revokeAccess() {
        mAuth.signOut();
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(signOutCallback);
    }

    private FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Timber.d(mActivity.getString(R.string.log_signin, user.getUid()));

            PrefUtils.storeGoogleUID(mActivity, user.getUid());
            PrefUtils.keepSignIn(mActivity);

            Intent intent = new Intent(mActivity, MainActivity.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }
    };

    private GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = connectionResult -> {
        Timber.d(mActivity.getString(R.string.log_connection_off, connectionResult.getErrorMessage()));
        Toast.makeText(mActivity, mActivity.getString(R.string.message_google_play_error), Toast.LENGTH_SHORT).show();
    };

    private OnCompleteListener<AuthResult> onCompleteListener = task -> {
        if (!task.isSuccessful()) {
            Toast.makeText(mActivity, mActivity.getString(R.string.message_auth_failed), Toast.LENGTH_SHORT).show();
        }
    };

    private ResultCallback<Status> signOutCallback = status -> {
        Timber.d(mActivity.getString(R.string.log_signout));
        mSignOutListener.onSignOutSuccess();
    };


    public interface OnSignOutListener {
        void onSignOutSuccess();
    }

}
