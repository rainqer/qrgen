package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.google.zxing.WriterException;

import pl.touk.qrgen.service.QrCodeGenerator;
import pl.touk.qrgen.service.QrPlainTextCodeGenerator;
import rx.Observable;

public class PlainTextQrFragment extends QrFragment {

    @Nullable
    @Override
    protected Observable<Bitmap> getQrGenerationObservable() throws WriterException {
        String userStringForTranslation = extractPlainTextFromIntent();
        return userStringForTranslation != null
                ? getQrCodeGenerator().generate(getActivity(), userStringForTranslation)
                : null;
    }

    private String extractPlainTextFromIntent() {
        return getActivity().getIntent()
                .getStringExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY);
    }

    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrPlainTextCodeGenerator();
    }
}
