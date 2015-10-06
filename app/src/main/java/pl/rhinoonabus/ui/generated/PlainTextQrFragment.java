package pl.rhinoonabus.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.zxing.WriterException;

import pl.rhinoonabus.service.QrCodeGenerator;
import pl.rhinoonabus.service.QrPlainTextCodeGenerator;
import rx.Observable;

public class PlainTextQrFragment extends QrFragment {

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
        return getActivity().getIntent()
                .getStringExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY);
    }

    @Override
    protected boolean shouldShowLaunchLinkButton() {
        return false;
    }

    @Override
    protected boolean shouldShowDialButton() {
        return false;
    }

    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrPlainTextCodeGenerator();
    }
}
