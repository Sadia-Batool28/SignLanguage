package com.example.startproject;

        import android.util.Patterns;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginEmail, etLoginPassword;
    private Button btnLogin;
    private TextView tvForgotPassword, tvSignUp;
    private ImageView imgLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
        imgLogin = findViewById(R.id.imgLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etLoginEmail.getText().toString().trim();
                String password = etLoginPassword.getText().toString();

                // Email validation (must end with gmail.com)
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
                    etLoginEmail.setError("Valid email ending with @gmail.com is required");
                    return;
                }
                // Password validation (length and complexity checks)
                if (TextUtils.isEmpty(password) || password.length() < 8 || password.length() > 15) {
                    etLoginPassword.setError("Password must be between 8 and 15 characters");
                    return;
                }

                // Perform login logic here
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Navigate to home screen after successful login
                // startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                // finish();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ForgotPasswordActivity when "Forgot Password?" is clicked
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignupActivity
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish(); // Optional: finish LoginActivity to prevent going back to it
            }
        });
    }
}
