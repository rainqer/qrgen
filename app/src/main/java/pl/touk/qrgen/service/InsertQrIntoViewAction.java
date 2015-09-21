package pl.touk.qrgen.service;

import android.graphics.Bitmap;
import android.widget.ImageView;

import rx.functions.Action1;

public class InsertQrIntoViewAction implements Action1<Bitmap> {

    private final ImageView imageView;

    public InsertQrIntoViewAction(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void call(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
