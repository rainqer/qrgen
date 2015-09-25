package pl.touk.qrgen.service;

import android.content.Intent;

import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;

public class QrPhoneCodeGenerator extends QrCodeGenerator {

    public QrPhoneCodeGenerator() {
        super();
    }

    public QrPhoneCodeGenerator(int customSize) {
        super(customSize);
    }

    @Override
    protected Intent applyEncodeType(Intent intent) {
        return intent.putExtra(Intents.Encode.TYPE, Contents.Type.PHONE);
    }
}
