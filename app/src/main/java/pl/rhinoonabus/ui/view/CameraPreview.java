/*
 * Barebones implementation of displaying camera preview.
 */
package pl.rhinoonabus.ui.view;

import java.io.IOException;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.AutoFocusCallback;

import io.fabric.sdk.android.Fabric;

//TODO refactor this borrowed class
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CameraPreview";
    private static final String ERROR_STOPPING_CAMERA_PREVIEW = "Error stopping camera preview";
    private static final String ERROR_STARTING_CAMERA_PREVIEW = "Error starting camera preview";
    private static final String ERROR_SETTING_CAMERA_PREVIEW = "Error setting camera preview";

    private SurfaceHolder mHolder;
    private Camera mCamera;
    private PreviewCallback previewCallback;
    private AutoFocusCallback autoFocusCallback;

    public CameraPreview(Context context, Camera camera,
                         PreviewCallback previewCb,
                         AutoFocusCallback autoFocusCb) {
        super(context);
        mCamera = camera;
        previewCallback = previewCb;
        autoFocusCallback = autoFocusCb;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            Fabric.getLogger().e(TAG, ERROR_SETTING_CAMERA_PREVIEW, e);
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mHolder.getSurface() == null){
          return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            Fabric.getLogger().e(TAG, ERROR_STOPPING_CAMERA_PREVIEW, e);
        }

        try {
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setPreviewCallback(previewCallback);
            mCamera.startPreview();
            mCamera.autoFocus(autoFocusCallback);
        } catch (Exception e){
            Fabric.getLogger().e(TAG, ERROR_STARTING_CAMERA_PREVIEW, e);
        }
    }
}
