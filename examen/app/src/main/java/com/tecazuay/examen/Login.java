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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText txtmailLogin;
    EditText txtpasswordLogin;
    Button btnLogin;
    TextView registercall;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        try {
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if (currentUser != null) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this,"No se puede autenticar", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        registercall = findViewById(R.id.textView_registercall);

        btnLogin.setOnClickListener(l -> LoginCall());
        registercall.setOnClickListener(l -> RegisterNow());
    }



    public void RegisterNow() {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
        finish();
    }

    public void LoginCall() {

        txtmailLogin = findViewById(R.id.txt_mail_login);
        txtpasswordLogin = findViewById(R.id.txt_password_login);
        mAuth = FirebaseAuth.getInstance();

        String email = String.valueOf(txtmailLogin.getText());
        String password = String.valueOf(txtpasswordLogin.getText());

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Ingrese " + (TextUtils.isEmpty(email) ? "un email" : "una contraseña") , Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Ingreso exitoso.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Datos no válidos.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}