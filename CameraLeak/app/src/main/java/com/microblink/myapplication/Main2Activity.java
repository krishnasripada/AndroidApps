package com.microblink.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Main2Activity extends Activity {

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
     public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
     protected void onResume() {
        super.onResume();

        checkCameraType();
    }

    private void checkCameraType() {
        try {
            String supportType = getCamera2SupportType(getApplicationContext());
            Intent ri = new Intent();
            ri.putExtra("cameraMode", supportType);
            setResult(RESULT_OK, ri);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private String getCamera2SupportType(Context context) throws CameraAccessException {
        CameraManager  manager = (CameraManager) context.getApplicationContext().getSystemService(Context.CAMERA_SERVICE);
        String cameraId = getCameraId(manager);

        CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);

        int supportedHwLevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);

        if (supportedHwLevel == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL) return "FULL";
        if (supportedHwLevel == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED) return "LIMITED";
        return "LEGACY";
    }

    private static String getCameraId(CameraManager manager) throws CameraAccessException {
        String[] availableCameras = manager.getCameraIdList();

        for (int i = 0; i < availableCameras.length; ++i) {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(availableCameras[i]);
            if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK) {
                return availableCameras[i];
            }
        }

        return availableCameras[0];
    }
}
