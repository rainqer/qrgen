package pl.touk.qrgen.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.service.InsertQrIntoViewAction;
import pl.touk.qrgen.service.QrCodeGenerator;
import pl.touk.qrgen.service.QrPlainTextCodeGenerator;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class QrCreatorFragment extends Fragment {

    @Bind(R.id.qr_image_view) ImageView qrCodeImageView;
    private QrCodeGenerator qrCodeGenerator = new QrPlainTextCodeGenerator();
    private Subscription qrCodeGenerationSubscription = Subscriptions.empty();
    private FloatingActionButtonOverlay floatingActionButtonOverlay = new FloatingActionButtonOverlay();

    public static QrCreatorFragment newInstance() {
        QrCreatorFragment fragment = new QrCreatorFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_qr_creator, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButtonOverlay.attach(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        floatingActionButtonOverlay.animateEntry(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        qrCodeGenerationSubscription.unsubscribe();
    }

    @Override
    public void onStart() {
        super.onStart();
        createQrCode();
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
        qrCodeGenerationSubscription = qrCodeGenerator.generate(getActivity(), "aaa")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new InsertQrIntoViewAction(qrCodeImageView));
    }
}
