package com.example.machara;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextRegisterName, editTextRegisterEmail, editTextRegisterDoB, editTextRegisterMobile, editTextRegisterPassword,
            editTextRegisterConfPassword;

    private ProgressBar progressBar;
    private RadioGroup radioGroupRegisterGender;
    private RadioButton radioButtonRegisterGenderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toast.makeText(RegisterActivity.this, "you can register now", Toast.LENGTH_LONG).show();

        progressBar = findViewById(R.id.progressBar);

        editTextRegisterName = findViewById(R.id.editfullnameReg);
        editTextRegisterEmail = findViewById(R.id.editemailReg);
        editTextRegisterDoB = findViewById(R.id.editbirthdayReg);
        editTextRegisterMobile = findViewById(R.id.editmobphoneReg);
        editTextRegisterPassword = findViewById(R.id.editpasswordReg);
        editTextRegisterConfPassword = findViewById(R.id.editconfirmReg);
        
        radioGroupRegisterGender = findViewById(R.id.radio_group_register_gender);
        radioGroupRegisterGender.clearCheck();

        Button buttonRegister = findViewById(R.id.registrationButton);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGenderId = radioGroupRegisterGender.getCheckedRadioButtonId();
                radioButtonRegisterGenderSelected = findViewById(selectedGenderId);
                
                String textFullName = editTextRegisterName.getText().toString();
                String textEmail = editTextRegisterEmail.getText().toString().trim();
                String textDoB = editTextRegisterDoB.getText().toString();
                String textMobile = editTextRegisterMobile.getText().toString();
                String textPassword = editTextRegisterPassword.getText().toString();
                String textConfPassword = editTextRegisterConfPassword.getText().toString();
                String textGender;
                
                if (TextUtils.isEmpty(textFullName)){
                    Toast.makeText(RegisterActivity.this, "please enter your name", Toast.LENGTH_LONG).show();
                    editTextRegisterName.setError("name is required");
                    editTextRegisterName.requestFocus();
                    
                }
                else if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(RegisterActivity.this, "please enter your email", Toast.LENGTH_LONG).show();
                    editTextRegisterEmail.setError("email is required");
                    editTextRegisterEmail.requestFocus();
                }
                else if (Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(RegisterActivity.this, "invalid email", Toast.LENGTH_LONG).show();
                    editTextRegisterEmail.setError("invalid email");
                    editTextRegisterEmail.requestFocus();
                }
                else if (TextUtils.isEmpty(textDoB)){
                    Toast.makeText(RegisterActivity.this, "please enter your date of birth", Toast.LENGTH_LONG).show();
                    editTextRegisterDoB.setError("date of birth is required");
                    editTextRegisterDoB.requestFocus();
                }
                else if(radioGroupRegisterGender.getCheckedRadioButtonId() == -1){
                    Toast.makeText(RegisterActivity.this, "please select your gender", Toast.LENGTH_LONG).show();
                    radioButtonRegisterGenderSelected.setError("gender is required");
                    radioButtonRegisterGenderSelected.requestFocus();
                }
                else if (TextUtils.isEmpty(textMobile)){
                    Toast.makeText(RegisterActivity.this, "please enter your mobile no.", Toast.LENGTH_LONG).show();
                    editTextRegisterMobile.setError("mobile no. is required");
                    editTextRegisterMobile.requestFocus();
                }
                else if (textMobile.length() != 10 ){
                    Toast.makeText(RegisterActivity.this, "invalid mobile no.", Toast.LENGTH_LONG).show();
                    editTextRegisterMobile.setError("invalid mobile no.");
                    editTextRegisterMobile.requestFocus();
                }
                else if (TextUtils.isEmpty(textPassword)){
                    Toast.makeText(RegisterActivity.this, "please enter your password", Toast.LENGTH_LONG).show();
                    editTextRegisterPassword.setError("password is required");
                    editTextRegisterPassword.requestFocus();
                }
                else if (textPassword.length() < 6){
                    Toast.makeText(RegisterActivity.this, "password should be at least 6 digits", Toast.LENGTH_LONG).show();
                    editTextRegisterPassword.setError("password is too short");
                    editTextRegisterPassword.requestFocus();
                }
                else if (TextUtils.isEmpty(textConfPassword)){
                    Toast.makeText(RegisterActivity.this, "please confirm your password", Toast.LENGTH_LONG).show();
                    editTextRegisterConfPassword.setError("password confirmation is required");
                    editTextRegisterConfPassword.requestFocus();
                }
                else if (!textPassword.equals(textConfPassword)) {
                    Toast.makeText(RegisterActivity.this, "passwords do not match", Toast.LENGTH_LONG).show();
                    editTextRegisterConfPassword.setError("passwords do not match");
                    editTextRegisterConfPassword.requestFocus();
                    editTextRegisterPassword.clearComposingText();
                    editTextRegisterConfPassword.clearComposingText();
                }
                else {
                    textGender = radioButtonRegisterGenderSelected.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textFullName, textDoB, textEmail, textMobile, textPassword, textGender);
                    
                }
                
            }
        });
    }

    private void registerUser(String textFullName, String textDoB, String textEmail, String textMobile, String textPassword, String textGender) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "user is created", Toast.LENGTH_LONG).show();
                            firebaseUser.sendEmailVerification();


//                            Intent intent = new Intent(RegisterActivity.this, UserProfileActivity.class);
//                            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
//                            |Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
                        }
                    }
                });
    }
}