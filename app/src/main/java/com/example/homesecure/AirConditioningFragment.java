package com.example.homesecure;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class AirConditioningFragment extends Fragment {

    private static final String TAG = "LIFECYCLE";

    private static final int MIN_TEMP = 16;

    private SeekBar sbTemperature;
    private TextView tvTemperature;
    private TextView tvAcStatus;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "AirConditioningFragment: onCreateView");
        View view = inflater.inflate(R.layout.fragment_air_conditioning, container, false);

        sbTemperature = view.findViewById(R.id.sb_temperature);
        tvTemperature = view.findViewById(R.id.tv_temperature);
        tvAcStatus = view.findViewById(R.id.tv_ac_status);

        // Temperature seekbar logic (range 16°C to 30°C)
        sbTemperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTemperatureDisplay(progress + MIN_TEMP);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Initialize display
        updateTemperatureDisplay(sbTemperature.getProgress() + MIN_TEMP);

        return view;
    }

    private void updateTemperatureDisplay(int tempC) {
        if (getContext() == null) return;

        tvTemperature.setText(tempC + "°C");

        if (tempC < 21) {
            tvAcStatus.setText(getString(R.string.ac_status_cooling_high));
            tvAcStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.accent_cyan));
        } else if (tempC <= 25) {
            tvAcStatus.setText(getString(R.string.ac_status_cooling_optimal));
            tvAcStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.status_green));
        } else {
            tvAcStatus.setText(getString(R.string.ac_status_heating));
            tvAcStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.amber_warm));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "AirConditioningFragment: onDestroyView");
    }
}
