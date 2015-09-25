package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.google.zxing.WriterException;

import pl.touk.qrgen.service.QrCodeGenerator;
import pl.touk.qrgen.service.QrPhoneCodeGenerator;
import pl.touk.qrgen.service.QrPlainTextCodeGenerator;
import rx.Observable;

public class PhoneQrFragment extends QrFragment {

    @Nullable
    @Override
    protected Observable<Bitmap> getQrGenerationObservable() throws WriterException {
        String userStringForTranslation = extractPhoneFromIntent();
        return userStringForTranslation != null
                ? getQrCodeGenerator().generate(getActivity(), userStringForTranslation)
                : null;
    }

    private String extractPhoneFromIntent() {
        return getActivity().getIntent()
                .getStringExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY);
    }

    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrPhoneCodeGenerator();
    }
}
