package pl.touk.qrgen.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.zxing.WriterException;
import com.google.zxing.client.android.encode.QRCodeEncoder;

public class QrCodeImageGenerator {
    private QRCodeEncoder encoder;
    private static final String ENCODE = "com.google.zxing.client.android.ENCODE";

    public Bitmap generate(@NonNull Context context, String content) throws WriterException {
        initEncoderIfNecessary(context);
        return encoder.encodeAsBitmap();
    }

    private void initEncoderIfNecessary(@NonNull Context context) throws WriterException {
        if (encoder == null) {
            Intent encoderIntent = new Intent(ENCODE);
            encoderIntent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
            encoderIntent.putExtra("ENCODE_DATA", "aaaaa");
            encoder = new QRCodeEncoder(context, encoderIntent, 300, false);
        }
    }
}
