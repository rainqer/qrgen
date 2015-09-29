package com.google.zxing.client.android.decode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.google.zxing.client.android.decode.view.BoundingView;
import com.google.zxing.client.android.decode.view.CameraPreviewView;

import pl.touk.qrgen.R;

/**
 * Capture activity (camera barcode activity)
 */
public class CaptureActivity extends Activity {
    public static String PUBLIC_STATIC_STRING_IDENTIFIER;

    /**
     * Camera preview view
     */
    private CameraPreviewView cameraPreview;
    /**
     * Camera manager
     */
    private CameraManager cameraManager;

    /**
     * Capture handler
     */
    private Handler captureHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        initializeCamera();
        initializeCameraPreview();
        initializeBoundingView();
    }

    private View getContentView() {
        RelativeLayout contentView = new RelativeLayout(this);
        contentView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        contentView.addView(getBoundingView());
        contentView.addView(getCameraPreviewView());

        return contentView;
    }

    private CameraPreviewView getCameraPreviewView() {
        //This is the camera SurfaceView
        CameraPreviewView camPreview = new CameraPreviewView(this);
        camPreview.setId(R.id.camera_preview);
        camPreview.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        return camPreview;
    }

    private BoundingView getBoundingView() {
        //Displays the bounding content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        BoundingView boundingView = new BoundingView(this);
        boundingView.setId(R.id.bounding_view);
        boundingView.setLayoutParams(params);

        return boundingView;
    }

    private void initializeCamera() {
        // Create an instance of Camera
        cameraManager = new CameraManager();
        captureHandler = new CaptureHandler(cameraManager, this, new OnDecoded());

        //requesting next frame for decoding
        cameraManager.requestNextFrame(new PreviewCallback(captureHandler, cameraManager));
    }

    private void initializeCameraPreview() {
        // Create our Preview view and set it as the content of our activity.
        cameraPreview = (CameraPreviewView) findViewById(R.id.camera_preview);
        cameraPreview.setCameraManager(cameraManager);
    }

    private void initializeBoundingView() {
        //Set the cameraManager to the bounding view
        ((BoundingView) findViewById(R.id.bounding_view)).setCameraManager(cameraManager);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //We don't want the cameraManager to take up unneeded
        //resources so we release it from memory onPause
        cameraManager.release();
    }

    /**
     * This handles the decoded content from the Zxing framework. As
     * soon as the framework has completed the decoding process the CaptureHandler
     * will pass it back via this listener for you to handle it further.
     *
     */
    private class OnDecoded implements CaptureHandler.OnDecodedCallback {
        @Override
        public void onDecoded(String decodedData) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER, decodedData);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}