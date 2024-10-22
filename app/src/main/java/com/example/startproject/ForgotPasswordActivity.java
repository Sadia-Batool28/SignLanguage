package com.example.startproject;

        import android.util.Patterns;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.etEmail);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();

                // Email validation (must end with gmail.com)
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
                    etEmail.setError("Valid email ending with @gmail.com is required");
                    return;
                }

                // Logic for handling password reset
                Toast.makeText(ForgotPasswordActivity.this, "Password reset link sent to " + email, Toast.LENGTH_SHORT).show();
                // Optionally, navigate back to the login screen after submitting
                finish();
            }
        });
    }
}
