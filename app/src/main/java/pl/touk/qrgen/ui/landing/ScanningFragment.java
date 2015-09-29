package pl.touk.qrgen.ui.landing;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import pl.touk.qrgen.R;
import pl.touk.qrgen.events.GenerateCodePageSelectedEvent;
import pl.touk.qrgen.events.ScanCodePageSelectedEvent;

public class ScanningFragment extends Fragment {

    @Inject Bus bus;
    @Bind(R.id.cameraPreview) FrameLayout cameraPreview;
    private Camera mCamera;
    private Handler autoFocusHandler;
    private boolean previewing = true;
    TextView scanText;
    ImageScanner scanner;

    static {
        System.loadLibrary("iconv");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scanning, null);
        ButterKnife.bind(this, view);
        ((LandingActivityComponentProvider) getActivity()).getComponent().inject(this);
        return view;
    }

    @Subscribe
    public void onScanCodeSelected(ScanCodePageSelectedEvent event) {
        initScanner();
    }

    @Subscribe
    public void onGenerateCodeSelected(GenerateCodePageSelectedEvent event) {
        killScanner();
    }

    private void initScanner() {
        mCamera = getCameraInstance();
        cameraPreview.addView(new CameraPreview(getActivity(), mCamera, previewCb, autoFocusCB));
        scanText = (TextView)getActivity().findViewById(R.id.scanText);
        scanText.setText("Scanning...");
        mCamera.setPreviewCallback(previewCb);
        mCamera.startPreview();
        previewing = true;
        mCamera.autoFocus(autoFocusCB);
    }

    private void killScanner() {
        previewing = false;
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
        }
        releaseCamera();
        cameraPreview.removeAllViews();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autoFocusHandler = new Handler();

        /* Instance barcode scanner */
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

    }

    @Override
    public void onResume() {
        super.onResume();
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

    /** A safe way to get an instance of the Camera object. */
    private static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e){
        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
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
                    scanText.setText("barcode result " + sym.getData());
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
}
