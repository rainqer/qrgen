package pl.rhinoonabus.ui.generated;

import android.content.Intent;
import android.support.annotation.NonNull;
import pl.rhinoonabus.service.QrCodeGenerator;
import pl.rhinoonabus.service.QrPlainTextCodeGenerator;

public class PlainTextQrFragment extends QrFragment {

    @NonNull
    @Override
    protected String extractDataFromIntent() {
        return getActivity().getIntent()
                .getStringExtra(Intent.EXTRA_TEXT);
    }

    @Override
    protected boolean shouldShowLaunchLinkButton() {
        return false;
    }

    @Override
    protected boolean shouldShowDialButton() {
        return false;
    }

    @NonNull
    @Override
    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrPlainTextCodeGenerator();
    }
}
