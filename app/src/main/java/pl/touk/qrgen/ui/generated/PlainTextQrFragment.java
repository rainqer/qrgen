package pl.touk.qrgen.ui.generated;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.zxing.WriterException;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.service.QrCodeGenerator;
import pl.touk.qrgen.service.QrPlainTextCodeGenerator;
import rx.Observable;

public class PlainTextQrFragment extends QrFragment {

    @Bind(R.id.qr_image_view) ImageView qrCodeImageView;
    private QrCodeGenerator qrCodeGenerator = new QrPlainTextCodeGenerator();

    public static PlainTextQrFragment newInstance() {
        PlainTextQrFragment fragment = new PlainTextQrFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_qr_creator, container, false);
        ButterKnife.bind(this, content);
        return content;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Nullable
    @Override
    protected Observable<Bitmap> getQrGenerationObservable() throws WriterException {
        String userStringForTranslation = extractPlainTextFromIntent();
        return userStringForTranslation != null
                ? qrCodeGenerator.generate(getActivity(), userStringForTranslation)
                : null;
    }

    private String extractPlainTextFromIntent() {
        return getActivity().getIntent()
                .getStringExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY);
    }

    @NonNull
    @Override
    protected ImageView getQrReplaceableImageView() {
        return qrCodeImageView;
    }
}
