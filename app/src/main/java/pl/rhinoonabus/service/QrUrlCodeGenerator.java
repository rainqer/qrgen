package pl.rhinoonabus.service;

import android.content.Intent;

import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;

import pl.rhinoonabus.tools.UrlCorrector;

public class QrUrlCodeGenerator extends QrCodeGenerator {


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
        super.applyData(intent, new UrlCorrector().getInCorrectFormat(data));
    }

}
