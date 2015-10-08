package pl.rhinoonabus.ui.generated;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.service.QrCodeGenerator;

public class EmptyQrFragment extends QrFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qr_creator_empty, container, false);
    }

    @NonNull
    @Override
    protected String extractDataFromIntent() {
        return null;
    }

    @NonNull
    @Override
    protected QrCodeGenerator getQrCodeGenerator() {
        return null;
    }

    @Override
    protected boolean shouldShowLaunchLinkButton() {
        return false;
    }

    @Override
    protected boolean shouldShowDialButton() {
        return false;
    }

    @Override
    protected void tearDown() {
    }

    @Override
    protected void createQrCode() {
    }
}
