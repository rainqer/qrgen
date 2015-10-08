package pl.rhinoonabus.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Subscribe;
import butterknife.ButterKnife;
import pl.rhinoonabus.events.GenerateCodeButtonClickedEvent;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.generated.QrFragmentFactory;

public class PhoneDetailsFormFragment extends PlainTextDetailsFormFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translation_details_phone, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Nullable
    @Override
    public void setupLaunchIntent(Intent intent) {
        intent.putExtra(QrFragmentFactory.QR_GENERATION_PROVIDER_TYPE, QrFragmentFactory.PHONE.ordinal())
                .putExtra(Intent.EXTRA_TEXT, getUserInput());
    }

    @Subscribe
    public void generateCodeButtonClicked(GenerateCodeButtonClickedEvent event) {
        attemptLaunchingQrCodeActivity();
    }
}
