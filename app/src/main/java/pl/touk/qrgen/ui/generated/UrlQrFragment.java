package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.google.zxing.WriterException;

import pl.touk.qrgen.service.QrCodeGenerator;
import pl.touk.qrgen.service.QrPlainTextCodeGenerator;
import pl.touk.qrgen.service.QrUrlCodeGenerator;
import rx.Observable;

public class UrlQrFragment extends PlainTextQrFragment {

    @Override
    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrUrlCodeGenerator();
    }
}
