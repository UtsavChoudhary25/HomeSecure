package com.example.homesecure;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LIFECYCLE";

    private MaterialCardView cardLighting;
    private MaterialCardView cardAc;
    private MaterialCardView cardSecurity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity: onCreate");
        setContentView(R.layout.activity_main);

        cardLighting = findViewById(R.id.card_nav_lighting);
        cardAc = findViewById(R.id.card_nav_ac);
        cardSecurity = findViewById(R.id.card_nav_security);

        cardLighting.setOnClickListener(v -> {
            loadFragment(new LightingFragment());
            updateActiveCard(cardLighting);
        });

        cardAc.setOnClickListener(v -> {
            loadFragment(new AirConditioningFragment());
            updateActiveCard(cardAc);
        });

        cardSecurity.setOnClickListener(v -> {
            loadFragment(new SecurityFragment());
            updateActiveCard(cardSecurity);
        });

        // Load LightingFragment by default if first time
        if (savedInstanceState == null) {
            loadFragment(new LightingFragment());
            updateActiveCard(cardLighting);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void updateActiveCard(MaterialCardView activeCard) {
        int activeColor = ContextCompat.getColor(this, R.color.card_stroke_active);
        int inactiveColor = ContextCompat.getColor(this, R.color.card_stroke);

        cardLighting.setStrokeColor(cardLighting == activeCard ? activeColor : inactiveColor);
        cardLighting.setStrokeWidth(cardLighting == activeCard ? dpToPx(2) : dpToPx(1));

        cardAc.setStrokeColor(cardAc == activeCard ? activeColor : inactiveColor);
        cardAc.setStrokeWidth(cardAc == activeCard ? dpToPx(2) : dpToPx(1));

        cardSecurity.setStrokeColor(cardSecurity == activeCard ? activeColor : inactiveColor);
        cardSecurity.setStrokeWidth(cardSecurity == activeCard ? dpToPx(2) : dpToPx(1));
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy");
    }
}
