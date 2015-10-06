package pl.rhinoonabus.ui.generated;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.service.FileExportedBitmap;
import pl.rhinoonabus.service.ShareAction;
import pl.rhinoonabus.tools.ClipBoardExporter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class QrFragment extends Fragment {

    public static final String FAILED_TO_CREATE_QR_CODE = "Failed to create qr code";
    @Bind(R.id.qr_image_view) ImageView qrCodeImageView;
    @Bind(R.id.human_readable_data) TextView humanReadableDataTextView;
    @Bind(R.id.launch_link) Button launchLinkButton;
    @Bind(R.id.dial) Button dialNumberButton;
    private Subscription qrCodeGenerationSubscription = Subscriptions.empty();
    private BitmapHolder bitmapHolder = new BitmapHolder();

    @Nullable
    protected abstract Observable<Bitmap> getQrGenerationObservable() throws WriterException;

    @NonNull
    protected abstract String extractDataFromIntent();
    protected abstract String extractUserReadableDataFromIntent();
    protected abstract boolean shouldShowLaunchLinkButton();
    protected abstract boolean shouldShowDialButton();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_qr_creator, container, false);
        ButterKnife.bind(this, content);
        setupUi();
        return content;
    }

    private void setupUi() {
        humanReadableDataTextView.setText(extractDataFromIntent());
        if (shouldShowLaunchLinkButton()) {
            launchLinkButton.setVisibility(View.VISIBLE);
        }
        if (shouldShowDialButton()) {
            dialNumberButton.setVisibility(View.VISIBLE);
        }
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
            //TODO remove this exception from google lib asap
            Toast.makeText(getActivity(), FAILED_TO_CREATE_QR_CODE, Toast.LENGTH_LONG).show();
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

    @OnClick(R.id.copy_text)
    public void exportButtonClicked() {
        if (bitmapHolder.bitmap != null) {
            new ClipBoardExporter(extractDataFromIntent()).exportToClipBoard(getActivity());
        }
    }

    @OnClick(R.id.launch_link)
    public void launchLinkButtonClicked() {
        if (bitmapHolder.bitmap != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(extractDataFromIntent())));
        }
    }

    @OnClick(R.id.dial)
    public void dialNumberButtonClicked() {
        if (bitmapHolder.bitmap != null) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(extractDataFromIntent())));
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
