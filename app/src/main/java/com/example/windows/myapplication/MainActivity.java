package com.example.windows.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ProgressDialog progressDialog1;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateLogin(currentUser);
        progressDialog = new ProgressDialog(this);
        progressDialog1 = new ProgressDialog(this);
    }


    public void login(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragSpace, new LoginActivity()).commit();
    }

    public void createAccount(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragSpace, new CreateAccount()).commit();
    }

    public void continueWithGoogle(View view) {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 0);
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 0) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        if (task != null) {
            progressDialog.dismiss();
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            startActivity(intent);
        }
    }


    public void fragCreateAccount(View view) {
        EditText email = (EditText) findViewById(R.id.email);
        String emailVal = email.getText().toString();

        EditText pswd = (EditText) findViewById(R.id.password);
        String pswdVal = pswd.getText().toString();

        EditText pswd1 = (EditText) findViewById(R.id.password1);
        String pswdVal1 = pswd1.getText().toString();
        progressDialog.setMessage("Loading");
        progressDialog.show();
        if (!pswdVal.equals(pswdVal1)) {
            progressDialog.dismiss();
            pswd1.setError("Passwords don't match");
        } else {
            mAuth.createUserWithEmailAndPassword(emailVal.toString().trim(), pswdVal.toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information


                                FirebaseUser user = mAuth.getCurrentUser();
                                update(user);
                            } else {
                                // If sign in fails, display a message to the user.

                               update(null);
                            }
                        }
                    });
        }
    }

    public void update(FirebaseUser user)
    {
        if(user!=null){
            progressDialog.dismiss();
            Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(this,"Account already exists",Toast.LENGTH_LONG).show();
        }
    }

    public void fragLogin(View view)
    {
        EditText email = (EditText) findViewById(R.id.emailId);
        String emailVal = email.getText().toString();

        EditText pswd = (EditText) findViewById(R.id.pswd);
        String pswdVal = pswd.getText().toString();
        progressDialog1.setMessage("Logging in...");
        progressDialog1.show();
        mAuth.signInWithEmailAndPassword(emailVal.toString(), pswdVal.toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateLogin(user);
                            progressDialog1.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            progressDialog1.dismiss();
                            updateLogin(null);
                        }

                        // ...
                    }
                });


    }

    private void updateLogin(FirebaseUser user)
    {
        if(user!=null){
            //progressDialog1.dismiss();
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            startActivity(intent);
            Toast.makeText(this,"Logged in",Toast.LENGTH_LONG).show();
        }
        else
         {
             //progressDialog1.dismiss();
             Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();

         }
    }

}



