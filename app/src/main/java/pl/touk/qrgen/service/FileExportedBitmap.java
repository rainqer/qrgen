package pl.touk.qrgen.service;

import android.support.annotation.NonNull;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class FileExportedBitmap implements Future<String> {

    private static final String ERROR_MESSAGE = "Exporting qr bitmap failed";
    private final String filePath;
    private boolean done;

    public FileExportedBitmap(@NonNull String filePath) {
        this.filePath = filePath;
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
    public String get() {
        return encode();
    }

    @Override
    public String get(long timeout, TimeUnit unit) {
        return encode();
    }

    private String encode() {
//        final File dir = new File(Environment.getExternalStorageDirectory(), super.getResources().getString(R.string.config_external_storage_folder));
//
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        final File img = new File(dir, this.mNode.name + ".png");
//        if (img.exists()) {
//            img.delete();
//        }
//        final OutputStream outStream = new FileOutputStream(img);
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//        outStream.flush();
//        outStream.close();
        return "abc";
    }

//    public void shareimage () {
//        if (this.mImageDrawableSet == false) {
//            this.mApplyImageOnDisplay = true;
//            return;
//        }
//
//        try {
//            final Bitmap bitmap = getImageBitmap();
//            if (bitmap == null) {
//                Toast.makeText(getActivity(), "Something Went Wrong, Please Try Again!1", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            Intent share = new Intent(Intent.ACTION_SEND);
//            share.setType("image/*");
//            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(img));
//            startActivity(Intent.createChooser(share,"Share via"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(getActivity(), "Something Went Wrong, Please Try Again!2", Toast.LENGTH_SHORT).show();
//        }
//    }
}
