package pl.rhinoonabus.service;

import android.content.Intent;

import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;

public class QrPlainTextCodeGenerator extends QrCodeGenerator {

    public QrPlainTextCodeGenerator() {
        super();
    }

    public QrPlainTextCodeGenerator(int customSize) {
        super(customSize);
    }

    @Override
    protected Intent applyEncodeType(Intent intent) {
        return intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
    }
}
