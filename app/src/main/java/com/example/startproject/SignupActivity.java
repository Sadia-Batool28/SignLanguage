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

public class SignupActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnSignUp;
    private TextView tvLogin;
    private ImageView imgSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        imgSignup = findViewById(R.id.imgSignup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(fullName)) {
                    etFullName.setError("Full name is required");
                    return;
                }
                // Full name validation
                if (TextUtils.isEmpty(fullName)) {
                    etFullName.setError("Full name is required");
                    return;
                }
                // Email validation (must end with gmail.com)
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
                    etEmail.setError("Valid email ending with @gmail.com is required");
                    return;
                }
                // Phone validation (length check for at least 11 digits)
                if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                    etPhone.setError("Valid phone number with at least 11 digits is required");
                    return;
                }
                // Password validation (length and complexity checks)
                if (TextUtils.isEmpty(password) || password.length() < 8 || password.length() > 15) {
                    etPassword.setError("Password must be between 8 and 15 characters");
                    return;
                }
                // Confirm password validation
                if (!password.equals(confirmPassword)) {
                    etConfirmPassword.setError("Passwords do not match");
                    return;
                }
                // Password complexity check (at least one uppercase letter, one lowercase letter, one digit, and one special character)
                if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")) {
                    etPassword.setError("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character");
                    return;
                }

                // Perform sign-up logic here
                Toast.makeText(SignupActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity when "Already have an account? Log in" is clicked
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish(); // Optional: finish SignupActivity to prevent going back to it
            }
        });
    }
}
