package com.example.homesecure;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecurityStatusActivity extends AppCompatActivity {

    private static final String TAG = "LIFECYCLE";

    private TextView tvStatusMode;
    private Button btnBackToDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "SecurityStatusActivity: onCreate");
        setContentView(R.layout.activity_security_status);

        tvStatusMode = findViewById(R.id.tv_status_mode);
        btnBackToDashboard = findViewById(R.id.btn_back_to_dashboard);

        Intent intent = getIntent();
        String selectedMode = "Away";
        if (intent != null && intent.hasExtra("SECURITY_MODE")) {
            String extraMode = intent.getStringExtra("SECURITY_MODE");
            if (extraMode != null && !extraMode.isEmpty()) {
                selectedMode = extraMode;
            }
        }

        tvStatusMode.setText(getString(R.string.mode_formatted, selectedMode));

        btnBackToDashboard.setOnClickListener(v -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SecurityStatusActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SecurityStatusActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SecurityStatusActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SecurityStatusActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SecurityStatusActivity: onDestroy");
    }
}
