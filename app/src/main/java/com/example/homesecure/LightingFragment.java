package com.example.homesecure;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class LightingFragment extends Fragment {

    private static final String TAG = "LIFECYCLE";

    private RadioGroup rgRooms;
    private ToggleButton toggleLight;
    private SeekBar sbBrightness;
    private TextView tvBrightnessValue;
    private TextView tvLightStatus;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "LightingFragment: onCreateView");
        View view = inflater.inflate(R.layout.fragment_lighting, container, false);

        rgRooms = view.findViewById(R.id.rg_rooms);
        toggleLight = view.findViewById(R.id.toggle_light);
        sbBrightness = view.findViewById(R.id.sb_brightness);
        tvBrightnessValue = view.findViewById(R.id.tv_brightness_value);
        tvLightStatus = view.findViewById(R.id.tv_light_status);

        // Room selection listener
        rgRooms.setOnCheckedChangeListener((group, checkedId) -> updateLightState());

        // Light power toggle listener
        toggleLight.setOnCheckedChangeListener((buttonView, isChecked) -> updateLightState());

        // Brightness seekbar listener
        sbBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBrightnessValue.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Initialize state
        updateLightState();

        return view;
    }

    private void updateLightState() {
        if (getContext() == null) return;

        int selectedRoomId = rgRooms.getCheckedRadioButtonId();
        String roomName = "Living Room";

        if (selectedRoomId == R.id.rb_bedroom) {
            roomName = "Bedroom";
        } else if (selectedRoomId == R.id.rb_kitchen) {
            roomName = "Kitchen";
        } else if (selectedRoomId == R.id.rb_living_room) {
            roomName = "Living Room";
        } else {
            RadioButton selectedRb = rgRooms.findViewById(selectedRoomId);
            if (selectedRb != null) {
                roomName = selectedRb.getText().toString();
            }
        }

        boolean isOn = toggleLight.isChecked();
        if (isOn) {
            tvLightStatus.setText(getString(R.string.light_state_on, roomName));
            tvLightStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.status_green));
            sbBrightness.setEnabled(true);
        } else {
            tvLightStatus.setText(getString(R.string.light_state_off, roomName));
            tvLightStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.rose_alert));
            sbBrightness.setEnabled(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "LightingFragment: onDestroyView");
    }
}
