package pl.touk.qrgen.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.encode.QRCodeEncoder;

public abstract class QrCodeGenerator {
    private QRCodeEncoder encoder;
    protected static final int DEFAULT_QR_SIZE = 300;
    private final Integer qrSize;

    public QrCodeGenerator() {
        this(null);
    }

    public QrCodeGenerator(Integer qrSize) {
        this.qrSize = qrSize;
    }

    public Bitmap generate(@NonNull Context context, String content) throws WriterException {
        initEncoder(context, content);
        return encoder.encodeAsBitmap();
    }

    private void initEncoder(@NonNull Context context, String data) throws WriterException {
        encoder = new QRCodeEncoder(context, buildInitIntent(data), getQrSize(), false);
    }

    private Intent buildInitIntent(String data) {
        Intent encoderIntent = new Intent(Intents.Encode.ACTION);
        applyEncodeType(encoderIntent);
        encoderIntent.putExtra(Intents.Encode.DATA, data);
        return encoderIntent;
    }

    private int getQrSize() {
        return qrSize != null ? qrSize : DEFAULT_QR_SIZE;
    }

    protected abstract Intent applyEncodeType(Intent intent);
}
