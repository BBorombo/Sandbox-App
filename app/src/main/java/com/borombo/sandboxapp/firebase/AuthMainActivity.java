package com.borombo.sandboxapp.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.borombo.sandboxapp.config.ConfigFileManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class AuthMainActivity extends CommonActivity implements GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "twitter_key";
    private static final String TWITTER_SECRET = "twitter_secret";


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    private static final String TAG_GMAIL = "GmailAuth";
    private static final String TAG_FB = "FacebookAuth";
    private static final String TAG_TWITTER = "TwitterAuth";

    private CallbackManager callbackManager;

    @BindView(R.id.buttonTwitter)
    TwitterLoginButton twitterButton;
    @BindView(R.id.buttonFb)
    LoginButton facebookButton;

    @BindView(R.id.buttonMail)
    Button mailButton;
    @BindView(R.id.buttonGMail)
    Button gMailButton;
    @BindView(R.id.signOutButton)
    Button signOutButton;
    @BindView(R.id.accountHomePageButton)
    Button accountButton;
    @BindView(R.id.visibleFbButton)
    Button visibleFbButton;
    @BindView(R.id.visibleTwiButton)
    Button visibleTwiButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(ConfigFileManager.getConfigValue(this,TWITTER_KEY), ConfigFileManager.getConfigValue(this,TWITTER_SECRET));
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_firebase_auth_main);

        ButterKnife.bind(this);

        setUpActionBar(getString(R.string.firebase_authentication), ContextCompat.getColor(this, R.color.firebase_dark));

        // Visible button will be placed in front of the real one to use my own design, so I perform
        // the click when user click on the visible one.
        visibleFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookButton.performClick();
            }
        });

        visibleTwiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twitterButton.performClick();
            }
        });


        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthMainActivity.this, HomeProfileActivity.class);
                startActivity(intent);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser() != null){
                    mAuth.signOut();
                }
            }
        });

        // Authentication listener setup
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d(TAG_GMAIL, "onAuthStateChanged:signed_in:" + user.getUid());
                    accountButton.setVisibility(View.VISIBLE);
                    signOutButton.setEnabled(true);
                }else{
                    Log.d(TAG_GMAIL, "onAuthStateChanged:signed_out");
                    accountButton.setVisibility(View.GONE);
                    signOutButton.setEnabled(false);
                }
            }
        };



        twitterButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG_TWITTER, "twitterLogin:success" + result);
                handleTwitterSessison(result.data);

            }

            @Override
            public void failure(TwitterException exception) {
                Log.w(TAG_TWITTER, "twitterLogin:failure", exception);
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthMainActivity.this, MailAuthActivity.class);
                startActivity(intent);
            }
        });

        gMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        // Facebook connexion setup
        callbackManager = CallbackManager.Factory.create();
        facebookButton.setReadPermissions("email","public_profile");
        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG_FB, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG_FB, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG_FB, "facebook:onError", error);
            }
        });
    }

    /**
     * Handle the connexion with a twitter account
     * @param session
     */
    private void handleTwitterSessison(TwitterSession session) {
        Log.d(TAG_TWITTER, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);
        credentialFirebaseAuth(credential);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /**
     * Pass the activity to the GoogleSignIn Process
     */
    private void googleSignIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            // Google Sign in result
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }else{
                // TODO : Handle Fail GOogle AUth
            }
        }else if(requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE){
            // Twitter Sign in result
            twitterButton.onActivityResult(requestCode, resultCode, data);
        }else{
            // Facebook Sign in result
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Handle the connexion with credentials
     * @param credential
     */
    private void credentialFirebaseAuth(AuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG_GMAIL, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()){
                            Log.w(TAG_GMAIL, "signInWithCredential", task.getException());
                            Toast.makeText(AuthMainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(AuthMainActivity.this, HomeProfileActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG_GMAIL, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        credentialFirebaseAuth(credential);

    }

    private void handleFacebookAccessToken(AccessToken token){
        Log.d(TAG_FB, "handleFacebookAccessToken:" + token);

        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        credentialFirebaseAuth(credential);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG_GMAIL, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
