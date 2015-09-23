package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.zxing.WriterException;
import pl.touk.qrgen.service.InsertQrIntoViewAction;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class QrFragment extends Fragment{

    private Subscription qrCodeGenerationSubscription = Subscriptions.empty();

    @Nullable
    protected abstract Observable<Bitmap> getQrGenerationObservable() throws WriterException;

    @NonNull
    protected abstract ImageView getQrReplaceableImageView();

    @Override
    public void onStart() {
        super.onStart();
        createQrCode();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        qrCodeGenerationSubscription.unsubscribe();
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
                    .subscribe(new InsertQrIntoViewAction(getQrReplaceableImageView()));
        }
    }
}
