package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.zxing.WriterException;

import pl.touk.qrgen.service.QrCodeGenerator;
import pl.touk.qrgen.service.QrPhoneCodeGenerator;
import rx.Observable;

public class PhoneQrFragment extends QrFragment {

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

    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrPhoneCodeGenerator();
    }
}
