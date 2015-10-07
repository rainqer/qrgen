package pl.rhinoonabus.ui.generated;

import android.support.annotation.NonNull;
import pl.rhinoonabus.service.QrCodeGenerator;
import pl.rhinoonabus.service.QrUrlCodeGenerator;

public class UrlQrFragment extends PlainTextQrFragment {

    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";

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
        String rawData = super.extractDataFromIntent();
        String url = removeSpacesFromData(rawData);
        return hasHyperTextPrefix(url)
                ? url
                : HTTP_PREFIX + url;
    }

    private String removeSpacesFromData(String rawData) {
        return rawData.replace(" ", "");
    }

    private boolean hasHyperTextPrefix(String url) {
        return url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX);
    }
}
