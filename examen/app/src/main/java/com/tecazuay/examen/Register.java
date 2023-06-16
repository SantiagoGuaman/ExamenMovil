package com.tecazuay.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText txtmail;
    EditText txtpassword;
    Button btnregister;
    TextView loginback;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnregister = findViewById(R.id.btn_register);
        loginback = findViewById(R.id.textView_logincall);

        btnregister.setOnClickListener(l -> RegisterCall());
        loginback.setOnClickListener(l -> LoginNow());
    }

    public void RegisterCall() {

        txtmail = findViewById(R.id.txt_mail_register);
        txtpassword = findViewById(R.id.txt_password_register);

        mAuth = FirebaseAuth.getInstance();

        String email = String.valueOf(txtmail.getText());
        String password = String.valueOf(txtpassword.getText());

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(Register.this, "Ingrese " + (TextUtils.isEmpty(email) ? "un email" : "una contraseña de al menos 6 dígitos") , Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Cuenta creada.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Falló la autenticación.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void LoginNow() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }
}