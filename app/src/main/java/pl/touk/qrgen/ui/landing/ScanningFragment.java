package pl.touk.qrgen.ui.landing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.zxing.client.android.decode.CameraManager;
import com.google.zxing.client.android.decode.CaptureHandler;
import com.google.zxing.client.android.decode.PreviewCallback;
import com.google.zxing.client.android.decode.view.BoundingView;
import com.google.zxing.client.android.decode.view.CameraPreviewView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.events.GenerateCodePageSelectedEvent;
import pl.touk.qrgen.events.ScanCodePageSelectedEvent;

public class ScanningFragment extends Fragment {

    @Bind(R.id.content_view) RelativeLayout contentView;
    @Inject Bus bus;

    private CameraPreviewView cameraPreview;
    private CameraManager cameraManager;
    private Handler captureHandler;
    private BoundingView boundingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scanning, null);
        ButterKnife.bind(this, view);
        ((LandingActivityComponentProvider) getActivity()).getComponent().inject(this);
        return view;
    }

    public static String PUBLIC_STATIC_STRING_IDENTIFIER;

    @Subscribe
    public void scanCodePageSelected(ScanCodePageSelectedEvent event) {
        init();
    }

    @Subscribe
    public void generateCodePageSelected(GenerateCodePageSelectedEvent event) {
        tearDown();
    }

    private void init() {
        Toast.makeText(getActivity(), "INIT", Toast.LENGTH_SHORT).show();
        prepareContentView();
        initializeCamera();
        initializeCameraPreview();
        initializeBoundingView();
    }

    private void tearDown() {
        Toast.makeText(getActivity(), "TEAR DOWN", Toast.LENGTH_SHORT).show();
        if (cameraManager != null) {
            cameraManager.release();
            cameraManager = null;
        }
        if (cameraPreview != null) {
            cameraPreview.setCameraManager(null);
            cameraPreview = null;
        }
        if (boundingView != null) {
            boundingView.setCameraManager(null);
            boundingView = null;
        }
        contentView.removeAllViews();
    }

    private void prepareContentView() {
        contentView.addView(getBoundingView());
        contentView.addView(getCameraPreviewView());
    }

    private CameraPreviewView getCameraPreviewView() {
        //This is the camera SurfaceView
        cameraPreview = new CameraPreviewView(getActivity());
        cameraPreview.setId(R.id.camera_preview);
        cameraPreview.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return cameraPreview;
    }

    private BoundingView getBoundingView() {
        //Displays the bounding content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        boundingView = new BoundingView(getActivity());
        boundingView.setId(R.id.bounding_view);
        boundingView.setLayoutParams(params);

        return boundingView;
    }

    private void initializeCamera() {
        cameraManager = new CameraManager();
        captureHandler = new CaptureHandler(cameraManager, getActivity(), new OnDecoded());

        cameraManager.requestNextFrame(new PreviewCallback(captureHandler, cameraManager));
    }

    private void initializeCameraPreview() {
        cameraPreview.setCameraManager(cameraManager);
    }

    private void initializeBoundingView() {
        boundingView.setCameraManager(cameraManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);

        cameraManager.release();
    }

    private class OnDecoded implements CaptureHandler.OnDecodedCallback {
        @Override
        public void onDecoded(String decodedData) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER, decodedData);
//            setResult(Activity.RESULT_OK, resultIntent);
//            finish();
            Toast.makeText(getActivity(), "lalalal", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tearDown();
        ButterKnife.unbind(this);
    }
}
