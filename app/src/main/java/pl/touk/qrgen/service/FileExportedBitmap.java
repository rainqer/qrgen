package pl.touk.qrgen.service;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FileExportedBitmap implements Future<Uri> {

    private static final String ERROR_MESSAGE = "Exporting qr bitmap failed";
    private final String filePath;
    private final String fileName;
    private final Bitmap bitmap;
    private boolean done;

    public FileExportedBitmap(@NonNull String filePath, @NonNull String fileName, @NonNull Bitmap bitmap) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.bitmap = bitmap;
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
    public Uri get() throws ExecutionException {
        try {
            return exportToFile();
        } catch (IOException e) {
            throw new ExecutionException(e);
        }
    }

    @Override
    public Uri get(long timeout, TimeUnit unit) throws ExecutionException {
        try {
            return exportToFile();
        } catch (IOException e) {
            throw new ExecutionException(e);
        }
    }

    private Uri exportToFile() throws IOException {
        final File dir = new File(Environment.getExternalStorageDirectory(), filePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        final File img = new File(dir, fileName + ".png");
        if (img.exists()) {
            img.delete();
        }
        final OutputStream outStream = new FileOutputStream(img);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();
        return Uri.fromFile(img);
    }
}
