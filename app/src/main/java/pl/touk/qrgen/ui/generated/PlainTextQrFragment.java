package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.google.zxing.WriterException;

import pl.touk.qrgen.service.QrCodeGenerator;
import pl.touk.qrgen.service.QrPlainTextCodeGenerator;
import rx.Observable;

public class PlainTextQrFragment extends QrFragment {

    private QrCodeGenerator qrCodeGenerator = new QrPlainTextCodeGenerator();

    @Nullable
    @Override
    protected Observable<Bitmap> getQrGenerationObservable() throws WriterException {
        String userStringForTranslation = extractPlainTextFromIntent();
        return userStringForTranslation != null
                ? qrCodeGenerator.generate(getActivity(), userStringForTranslation)
                : null;
    }

    private String extractPlainTextFromIntent() {
        return getActivity().getIntent()
                .getStringExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY);
    }
}
