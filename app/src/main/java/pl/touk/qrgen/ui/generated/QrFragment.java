package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.touk.qrgen.R;
import pl.touk.qrgen.service.FileExportedBitmap;
import pl.touk.qrgen.service.ShareAction;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class QrFragment extends Fragment{

    @Bind(R.id.qr_image_view) ImageView qrCodeImageView;
    private Subscription qrCodeGenerationSubscription = Subscriptions.empty();
    private BitmapHolder bitmapHolder = new BitmapHolder();

    @Nullable
    protected abstract Observable<Bitmap> getQrGenerationObservable() throws WriterException;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_qr_creator, container, false);
        ButterKnife.bind(this, content);
        return content;
    }

    @Override
    public void onStart() {
        super.onStart();
        createQrCode();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        qrCodeGenerationSubscription.unsubscribe();
        ButterKnife.unbind(this);
    }

    private void createQrCode() {
        try {
            tryLaunchingAndSubscribingOnQrGeneration();
        } catch (WriterException e) {
            //TODO this situation is impossible, we only create encoder for action ENCODE.
            //TODO remove this exception asap
            Toast.makeText(getActivity(), "Failed to create qr code", Toast.LENGTH_LONG).show();
        }
    }

    private void tryLaunchingAndSubscribingOnQrGeneration() throws WriterException {
        Observable<Bitmap> bitmapGeneration = getQrGenerationObservable();
        if (bitmapGeneration != null) {
            qrCodeGenerationSubscription = bitmapGeneration
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(bitmapHolder);
        }
    }

    @OnClick(R.id.share)
    public void shareButtonClicked() {
        if (bitmapHolder.bitmap != null) {
            getObservableForBitmapExport()
                    .subscribeOn(Schedulers.computation())
                    .subscribe(new ShareAction(getActivity()));
        }
    }

    private Observable<Uri> getObservableForBitmapExport() {
        return Observable.from(
                new FileExportedBitmap(
                        getString(R.string.temporary_export_file_path),
                        getString(R.string.temporary_export_file_name),
                        bitmapHolder.bitmap)
        );
    }

    private class BitmapHolder implements Action1<Bitmap> {

        Bitmap bitmap;

        @Override
        public void call(Bitmap bitmap) {
            this.bitmap = bitmap;
            qrCodeImageView.setImageBitmap(bitmap);
        }
    }
}
