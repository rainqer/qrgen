package pl.rhinoonabus.ui.generated;

import android.support.annotation.NonNull;
import pl.rhinoonabus.service.QrCodeGenerator;
import pl.rhinoonabus.service.QrUrlCodeGenerator;
import pl.rhinoonabus.tools.UrlCorrector;

public class UrlQrFragment extends PlainTextQrFragment {

    @NonNull
    @Override
    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrUrlCodeGenerator();
    }

    @Override
    protected boolean shouldShowLaunchLinkButton() {
        return true;
    }

    @NonNull
    @Override
    protected String extractDataFromIntent() {
        return new UrlCorrector().getInCorrectFormat(super.extractDataFromIntent());
    }
}
