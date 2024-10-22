package com.example.startproject;

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

                if (TextUtils.isEmpty(email)) {
                    etLoginEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    etLoginPassword.setError("Password is required");
                    return;
                }

                // Perform login logic here (e.g., API call or database interaction)
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                // Navigate to the home screen after successful login
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
