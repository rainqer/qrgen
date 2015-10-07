package pl.rhinoonabus.service;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.encode.QRCodeEncoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.fabric.sdk.android.Fabric;

class GeneratedBitmap implements Future<Bitmap> {

    private static final String ERROR_MESSAGE = "Generating qr failed";
    private final QRCodeEncoder encoder;
    private boolean done;

    public GeneratedBitmap(@NonNull QRCodeEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public Bitmap get() throws InterruptedException, ExecutionException {
        return encode();
    }

    @Override
    public Bitmap get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return encode();
    }

    private Bitmap encode() {
        Bitmap result = null;
        try {
            result = encoder.encodeAsBitmap();
        } catch (WriterException e) {
            Fabric.getLogger().e(QrCodeGenerator.TAG, ERROR_MESSAGE, e);
        }
        done = true;
        return result;
    }
}
