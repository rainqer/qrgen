package pl.touk.qrgen.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.events.GenerateCodeButtonClickedEvent;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.generated.CodeGeneratedActivity;

public class UrlDetailsFormFragment extends QrGenerationDetailsForm {

    @Bind(R.id.qr_code_content) EditText userContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translation_details_url, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Nullable
    @Override
    public void setupLaunchIntent(Intent intent) {
        intent.putExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY, userContent.getText().toString());
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