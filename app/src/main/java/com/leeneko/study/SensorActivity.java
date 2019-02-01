package com.leeneko.study;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    SensorManager mSm;
    TextView tvLight, tvProxy, tvPress, tvOrient, tvMagenetic, tvAccel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mSm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tvLight = findViewById(R.id.tvLight);
        tvProxy = findViewById(R.id.tvProxy);
        tvPress = findViewById(R.id.tvPress);
        tvOrient = findViewById(R.id.tvOrient);
        tvMagenetic = findViewById(R.id.tvMagnetic);
        tvAccel = findViewById(R.id.tvAccel);

        List<Sensor> arSensor = mSm.getSensorList(Sensor.TYPE_ALL);
        StringBuilder log = new StringBuilder();
        log.append("size = " + arSensor.size() + "\n\n");
        for (Sensor s: arSensor) {
            log.append(String.format("name = %s, type = %d, vendor = %s, version = %d, power = %f, res = %f, range = %f\n\n",
                    s.getName(), s.getType(), s.getVendor(), s.getVersion(), s.getPower(), s.getResolution(), s.getMaximumRange()));
        }
        TextView tv = findViewById(R.id.tvLog);
        tv.setText(log.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        int delay = SensorManager.SENSOR_DELAY_UI;
        mSm.registerListener(mSensorEventListener, mSm.getDefaultSensor(Sensor.TYPE_LIGHT), delay);
        mSm.registerListener(mSensorEventListener, mSm.getDefaultSensor(Sensor.TYPE_PROXIMITY), delay);
        mSm.registerListener(mSensorEventListener, mSm.getDefaultSensor(Sensor.TYPE_PRESSURE), delay);
        mSm.registerListener(mSensorEventListener, mSm.getDefaultSensor(Sensor.TYPE_ORIENTATION), delay);
        mSm.registerListener(mSensorEventListener, mSm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), delay);
        mSm.registerListener(mSensorEventListener, mSm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSm.unregisterListener(mSensorEventListener);
    }

    SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) { } // 센서가 값을 반환하지 않았을 때
            float[] v = event.values;
            switch (event.sensor.getType()) {
                case Sensor.TYPE_LIGHT:
                    tvLight.setText(String.format("조도 = %f", v[0]));
                    break;
                case Sensor.TYPE_PROXIMITY:
                    tvProxy.setText(String.format("근접 = %f", v[0]));
                    break;
                case Sensor.TYPE_PRESSURE:
                    tvPress.setText(String.format("압력 = %f", v[0]));
                    break;
                case Sensor.TYPE_ORIENTATION:
                    tvOrient.setText(String.format("방향 x = %f, y = %f, z = %f", v[0], v[1], v[2]));
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    tvMagenetic.setText(String.format("자기 x = %f, y = %f, z = %f", v[0], v[1], v[2]));
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    tvAccel.setText(String.format("속도 x = %f, y = %f, z = %f", v[0], v[1], v[2]));
                    break;
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
