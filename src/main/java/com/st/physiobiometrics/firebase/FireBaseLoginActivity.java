package com.st.physiobiometrics.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.st.BlueSTSDK.gui.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.st.BlueSTSDK.gui.MainActivity;

public class FireBaseLoginActivity extends AppCompatActivity {
    EditText emailid;
    EditText password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_activity_login);

        mFirebaseAuth= FirebaseAuth.getInstance();
        //emailid=findViewById(R.id.EmailIdText);
        emailid=findViewById(R.id.EmailIdText);
        password=findViewById(R.id.PasswordText);
        btnSignIn=findViewById(R.id.SignInButton);
        tvSignUp=findViewById(R.id.SignUpButton);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser=mFirebaseAuth.getCurrentUser();

            if(mFireBaseUser != null){
                Toast.makeText(FireBaseLoginActivity.this,"You are logged in", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(FireBaseLoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
            else{
                Toast.makeText(FireBaseLoginActivity.this,"Please Login", Toast.LENGTH_SHORT).show();

            }

            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email=emailid.getText().toString();
                String email= emailid.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty() || pwd.isEmpty()) {
                    //emailid.setError("Please enter an email id");
                    Toast.makeText(FireBaseLoginActivity.this,"Fields are Empty!", Toast.LENGTH_SHORT).show();

                    emailid.requestFocus();
                }

                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(FireBaseLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(FireBaseLoginActivity.this,"Login Error,Please Login Again", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Intent inToHome=new Intent(FireBaseLoginActivity.this,MainActivity.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(FireBaseLoginActivity.this,"Error Occured!!", Toast.LENGTH_SHORT).show();

                }

            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp=new Intent(FireBaseLoginActivity.this,FirebaseSignupActivity.class);
                startActivity(intSignUp);

            }
        });
    }
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
