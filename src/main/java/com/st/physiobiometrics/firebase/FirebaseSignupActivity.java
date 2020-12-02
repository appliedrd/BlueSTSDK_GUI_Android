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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.st.BlueSTSDK.gui.R;

public class FirebaseSignupActivity extends AppCompatActivity {
    EditText emailid,password;
    EditText name,phone;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_activity_signup);


        mFirebaseAuth= FirebaseAuth.getInstance();
        emailid=findViewById(R.id.EmailIdText);
        password=findViewById(R.id.PasswordText);
        btnSignUp=findViewById(R.id.SignUpButton);
        tvSignIn=findViewById(R.id.SignInButton);
        name=findViewById(R.id.NameText);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=name.getText().toString();
                String email=emailid.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty() || pwd.isEmpty()||uname.isEmpty()) {
                    //emailid.setError("Please enter an email id");
                    Toast.makeText(FirebaseSignupActivity.this,"Fields are Empty!", Toast.LENGTH_SHORT).show();

                    emailid.requestFocus();
                }

                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(
                            FirebaseSignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(FirebaseSignupActivity.this,"SignUp Unsuccessful,Please Try Again",
                                        Toast.LENGTH_SHORT).show();

                            }
                            else{
                                startActivity(new Intent(FirebaseSignupActivity.this,FireBaseLoginActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(FirebaseSignupActivity.this,"Error Occured!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FirebaseSignupActivity.this,FireBaseLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
