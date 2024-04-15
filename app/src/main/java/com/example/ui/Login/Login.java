package com.example.ui.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ui.Pages.HomeActivity;
import com.example.ui.R;
import com.example.ui.databinding.ActivityMainBinding;
import com.example.ui.users;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Arrays;


//import okhttp3.internal.concurrent.Task;

public class Login extends AppCompatActivity {

    Button login;
    TextView signup, forgotpassword;
    ImageButton googleSignInButton, facebookSignInButton;
    CallbackManager mCallbackManager;
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient googleSignInClient;

    private FirebaseAuth firebaseAuth, mAuth;
    private static final String TAG= "GOOGLE_SIGN_IN_TAG";
    FirebaseUser user;
    FirebaseDatabase database;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();
        login = findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_login);
        forgotpassword = findViewById(R.id.forgot_password_login);
        googleSignInButton = findViewById(R.id.googlesigninbtn);
        facebookSignInButton=findViewById(R.id.facebook_btn_login);
        database= FirebaseDatabase.getInstance("https://signin-java-baac3-default-rtdb.firebaseio.com/");
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);

            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", keyHash);
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // User is already authenticated, navigate to HomeActivity
            Intent intent = new Intent(Login.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to the login screen
        }

        // Set OnClickListener for buttons
        login.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Verification.class);
            startActivity(intent);
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        });

        forgotpassword.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);
        });

        // Initialize Google SignIn options and client
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("932093723637-0j4ianvoe8koimqu5a3l872gqfjkrthp.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Set OnClickListener for Google SignIn button
        googleSignInButton.setOnClickListener(v -> signInWithGoogle());

        // Initialize Facebook login
        mCallbackManager = CallbackManager.Factory.create();

        // Set OnClickListener for Facebook SignIn button
        facebookSignInButton.setOnClickListener(v -> signInWithFacebook());
    }

    // Handle Google SignIn
    private void signInWithGoogle() {
        // Revoke access to the Google account
        googleSignInClient.revokeAccess().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "Revoke access successful");
                // Start the Google SignIn intent
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            } else {
                Log.w(TAG, "Revoke access failed", task.getException());
            }
        });
    }

    // Method to handle Facebook SignIn
    private void signInWithFacebook() {
        // Facebook login process
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        // CallbackManager for Facebook login result
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Log.d(TAG,"onActivityResult: Google Signin intent result");
            com.google.android.gms.tasks.Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                firebaseAuthWithGoogleAccount(account);
            }
            catch (Exception e){
                Log.d(TAG, "onActivityResult: "+e.getMessage());
            }
        }
        else {
            Log.d(TAG, "onActivityResul: helooo");
        }
    }

    // Facebook access token and Firebase authentication
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            user = mAuth.getCurrentUser();
                            assert user != null;
                            users user1=new users();
                            user1.setUserName(user.getDisplayName());
                            database.getReference().child("users").child(user.getUid()).setValue(user1);
                            Intent intent=new Intent(Login.this, HomeActivity.class);
                            intent.putExtra("name",user.getDisplayName());
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Firebase authentication with Google SignIn
    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG,"onSuccess: Logged In");
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    String uid = firebaseUser.getUid();
                    String email = firebaseUser.getEmail();
                    Log.d(TAG,"onSuccess: Email: "+email);
                    Log.d(TAG,"onSuccess: UID: "+uid);
                    if (authResult.getAdditionalUserInfo().isNewUser()){
                        Toast.makeText(Login.this, "Account Created...\n"+email, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Login.this, "Existing User...\n"+email, Toast.LENGTH_SHORT).show();
                    }
                    Intent intent=new Intent(Login.this, HomeActivity.class);
                    intent.putExtra("name", user.getDisplayName());
                    intent.putExtra("email", user.getEmail());
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> Log.d(TAG,"onFailure: Login Failed"+e.getMessage()));
    }
}