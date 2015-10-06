package pl.rhinoonabus.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.zxing.WriterException;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.encode.QRCodeEncoder;

import rx.Observable;

public abstract class QrCodeGenerator {

    static final String TAG = "QrCodeGenerator";
    private final Integer qrSize;
    protected static final int DEFAULT_QR_SIZE = 450;

    public QrCodeGenerator() {
        this(null);
    }

    public QrCodeGenerator(Integer qrSize) {
        this.qrSize = qrSize;
    }


    public Observable<Bitmap> generate(@NonNull Context context, String content) throws WriterException {
        return Observable.from(new GeneratedBitmap(createEncoder(context, content)));
    }

    private QRCodeEncoder createEncoder(@NonNull Context context, String data) throws WriterException {
        return new QRCodeEncoder(context, buildInitIntent(data), getQrSize(), false);
    }

    private Intent buildInitIntent(String data) {
        Intent encoderIntent = new Intent(Intents.Encode.ACTION);
        applyEncodeType(encoderIntent);
        applyData(encoderIntent, data);
        return encoderIntent;
    }

    protected void applyData(Intent intent, String data) {
        intent.putExtra(Intents.Encode.DATA, data);
    }

    private int getQrSize() {
        return qrSize != null ? qrSize : DEFAULT_QR_SIZE;
    }

    protected abstract Intent applyEncodeType(Intent intent);
}
