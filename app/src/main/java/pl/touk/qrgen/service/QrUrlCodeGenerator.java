package pl.touk.qrgen.service;

import android.content.Intent;

import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;

public class QrUrlCodeGenerator extends QrCodeGenerator {

    private static final String URL_PREFIX = "http://";

    public QrUrlCodeGenerator() {
        super();
    }

    public QrUrlCodeGenerator(int customSize) {
        super(customSize);
    }

    @Override
    protected Intent applyEncodeType(Intent intent) {
        return intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
    }

    @Override
    protected void applyData(Intent intent, String data) {
        super.applyData(intent, URL_PREFIX + data);
    }
}
