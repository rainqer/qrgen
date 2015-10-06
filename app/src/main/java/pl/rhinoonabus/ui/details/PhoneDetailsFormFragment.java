package pl.rhinoonabus.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.rhinoonabus.events.GenerateCodeButtonClickedEvent;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.generated.CodeGeneratedActivity;
import pl.rhinoonabus.ui.generated.QrFragmentFactory;

public class PhoneDetailsFormFragment extends QrGenerationDetailsForm {

    @Bind(R.id.qr_code_content) EditText userContent;
    @Bind(R.id.qr_code_type_top_title) TextView topTitle;
    @Bind(R.id.qr_code_type_icon) ImageView icon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
                .putExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY, userContent.getText().toString());
    }

    @Override
    protected View[] provideBlinkingViews() {
        return new View[] {topTitle, icon, userContent};
    }

    @Subscribe
    public void generateCodeButtonClicked(GenerateCodeButtonClickedEvent event) {
        if (!TextUtils.isEmpty(userContent.getText().toString())) {
            launchActivityWithQrCode();
        } else {
            showError();
        }
    }
}
