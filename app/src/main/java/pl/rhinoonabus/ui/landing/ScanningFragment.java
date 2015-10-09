package pl.rhinoonabus.ui.landing;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import pl.rhinoonabus.dagger.Components;
import pl.rhinoonabus.dagger.LandingActivityComponent;
import pl.rhinoonabus.events.GenerateCodePageSelectedEvent;
import pl.rhinoonabus.events.ScanCodePageSelectedEvent;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.generated.CodeGeneratedActivity;
import pl.rhinoonabus.ui.view.CameraPreview;

import static android.Manifest.permission.CAMERA;
import static android.support.v4.content.ContextCompat.checkSelfPermission;
import static pl.rhinoonabus.tools.PermissionRequestCodes.CAMERA_REQUEST_PERMISSION;

//TODO refactor this borrowed class, separate logic from views
public class ScanningFragment extends Fragment {

    private static final String TAG = "ScanningFragment";
    private static final String ERROR_MESSAGE = "failed setting up the camera";
    private static final int VIEW_QR_CODE_TRANSLATION = 11223;
    private static final String SCANNING_FRAGMENT_SELECTED = "scanningFragmentSelected";

    @Inject Bus bus;
    @Bind(R.id.resetScannerButton) View resetCameraButton;
    @Bind(R.id.cameraPreview) FrameLayout cameraPreview;
    private Camera mCamera;
    private Handler autoFocusHandler;
    private boolean previewing = true;
    private boolean scanningFragmentIsSelected;
    ImageScanner scanner;

    static {
        System.loadLibrary("iconv");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scanning, null);
        ButterKnife.bind(this, view);
        Components.<LandingActivityComponent>from(getActivity()).inject(this);
        restore(savedInstanceState);
        return view;
    }

    private void restore(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            scanningFragmentIsSelected = savedInstanceState.getBoolean(SCANNING_FRAGMENT_SELECTED);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SCANNING_FRAGMENT_SELECTED, scanningFragmentIsSelected);
    }

    @Subscribe
    public void onScanCodeSelected(ScanCodePageSelectedEvent event) {
        scanningFragmentIsSelected = true;
        hideResetButton();
        initScannerIfAllowed();
    }

    @Subscribe
    public void onGenerateCodeSelected(GenerateCodePageSelectedEvent event) {
        scanningFragmentIsSelected = false;
        killScanner();
    }

    private void initScannerIfAllowed() {
        if (checkSelfPermission(getActivity(), CAMERA) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{CAMERA}, CAMERA_REQUEST_PERMISSION);
        } else {
            tryOpeningCamera();
        }
    }

    private void tryOpeningCamera() {
        try {
            mCamera = Camera.open();
            cameraPreview.addView(new CameraPreview(getActivity(), mCamera, previewCb, autoFocusCB));
            mCamera.setPreviewCallback(previewCb);
            mCamera.startPreview();
            previewing = true;
        } catch (Exception e) {
            Fabric.getLogger().e(TAG, ERROR_MESSAGE, e);
        }
    }

    private void killScanner() {
        previewing = false;
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        cameraPreview.removeAllViews();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autoFocusHandler = new Handler();

        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (scanningFragmentIsSelected) {
            showResetButton();
        }
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        killScanner();
        bus.unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            int result = scanner.scanImage(barcode);

            if (result != 0) {
                killScanner();
                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {
                    Intent intent = CodeGeneratedActivity
                            .getIntentForSpecificContent(getActivity(), sym.getData());
                    startActivityForResult(intent, VIEW_QR_CODE_TRANSLATION);
                }
            }
        }
    };

    // Mimic continuous auto-focusing
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIEW_QR_CODE_TRANSLATION) {
            showResetButton();
        }
    }

    private void showResetButton() {
        resetCameraButton.setVisibility(View.VISIBLE);
    }

    private void hideResetButton() {
        resetCameraButton.setVisibility(View.GONE);
    }

    @OnClick(R.id.resetScannerButton)
    public void resetScannerButtonPressed() {
        hideResetButton();
        initScannerIfAllowed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        if (requestCode == CAMERA_REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initScannerIfAllowed();
            } else {
                showResetButton();
            }
        }
    }
}
