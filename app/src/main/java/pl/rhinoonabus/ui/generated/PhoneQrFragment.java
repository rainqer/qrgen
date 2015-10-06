package pl.rhinoonabus.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.zxing.WriterException;

import pl.rhinoonabus.service.QrCodeGenerator;
import pl.rhinoonabus.service.QrPhoneCodeGenerator;
import rx.Observable;

public class PhoneQrFragment extends QrFragment {

    private static final String PREFIX = "tel:";

    @Nullable
    @Override
    protected Observable<Bitmap> getQrGenerationObservable() throws WriterException {
        String userStringForTranslation = extractDataFromIntent();
        return userStringForTranslation != null
                ? getQrCodeGenerator().generate(getActivity(), userStringForTranslation)
                : null;
    }

    @NonNull
    @Override
    protected String extractDataFromIntent() {
        String phoneNumber = getActivity().getIntent()
                .getStringExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY);
        return phoneNumber.startsWith(PREFIX)
                ? phoneNumber
                : PREFIX + phoneNumber;
    }

    @Override
    protected boolean shouldShowLaunchLinkButton() {
        return false;
    }

    @Override
    protected boolean shouldShowDialButton() {
        return true;
    }

    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrPhoneCodeGenerator();
    }
}
