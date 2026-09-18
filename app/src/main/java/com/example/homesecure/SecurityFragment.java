package com.example.homesecure;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class SecurityFragment extends Fragment {

    private static final String TAG = "LIFECYCLE";
    private static final String CHANNEL_ID = "homesecure_security_channel";

    private RadioGroup rgSecurityModes;
    private TextView tvSelectedMode;

    private String currentSelectedMode = "Away";

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    executeSecurityActivation();
                } else {
                    Toast.makeText(getContext(), "Notification permission denied", Toast.LENGTH_SHORT).show();
                    // Still proceed with activity activation
                    openSecurityStatusActivity();
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "SecurityFragment: onCreateView");
        View view = inflater.inflate(R.layout.fragment_security, container, false);

        rgSecurityModes = view.findViewById(R.id.rg_security_modes);
        tvSelectedMode = view.findViewById(R.id.tv_selected_mode);
        Button btnActivateSecurity = view.findViewById(R.id.btn_activate_security);

        createNotificationChannel();

        // Radio group listener
        rgSecurityModes.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_mode_home) {
                currentSelectedMode = getString(R.string.mode_home);
            } else if (checkedId == R.id.rb_mode_night) {
                currentSelectedMode = getString(R.string.mode_night);
            } else {
                currentSelectedMode = getString(R.string.mode_away);
            }
            tvSelectedMode.setText(getString(R.string.selected_mode_label, currentSelectedMode));
        });

        // Activate button click listener
        btnActivateSecurity.setOnClickListener(v -> activateSecurity());

        // Initial setup
        tvSelectedMode.setText(getString(R.string.selected_mode_label, currentSelectedMode));

        return view;
    }

    private void activateSecurity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (getContext() != null &&
                    ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS)
                            != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                return;
            }
        }
        executeSecurityActivation();
    }

    private void executeSecurityActivation() {
        sendSecurityNotification();
        openSecurityStatusActivity();
    }

    @SuppressLint("MissingPermission")
    private void sendSecurityNotification() {
        if (getContext() == null) return;

        String title = getString(R.string.notification_title);
        String text = getString(R.string.notification_text, currentSelectedMode);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_shield)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private void openSecurityStatusActivity() {
        if (getActivity() == null) return;
        Intent intent = new Intent(getActivity(), SecurityStatusActivity.class);
        intent.putExtra("SECURITY_MODE", currentSelectedMode);
        startActivity(intent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && getContext() != null) {
            CharSequence name = getString(R.string.notification_channel_name);
            String description = getString(R.string.notification_channel_desc);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager =
                    getContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "SecurityFragment: onDestroyView");
    }
}
