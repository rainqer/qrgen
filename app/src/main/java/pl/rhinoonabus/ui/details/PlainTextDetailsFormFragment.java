package pl.rhinoonabus.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import pl.rhinoonabus.dagger.Components;
import pl.rhinoonabus.dagger.DetailsActivityComponent;
import pl.rhinoonabus.events.GenerateCodeButtonClickedEvent;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.generated.CodeGeneratedActivity;
import pl.rhinoonabus.ui.generated.QrFragmentFactory;

public class PlainTextDetailsFormFragment extends QrGenerationDetailsForm {

    private static final String USER_INPUT_KEY = "userInput";
    @Bind(R.id.qr_code_content) EditText userContent;
    @Bind(R.id.qr_code_type_top_title) TextView topTitle;
    @Bind(R.id.qr_code_type_icon) ImageView icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translation_details_plain_text, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Components.<DetailsActivityComponent>from(getActivity()).inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USER_INPUT_KEY, getUserInput());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            userContent.setText(savedInstanceState.getString(USER_INPUT_KEY));
        }
    }

    @Override
    protected View[] provideBlinkingViews() {
        return new View[]{topTitle, icon, userContent};
    }

    @Nullable
    @Override
    public void setupLaunchIntent(Intent intent) {
        intent.putExtra(QrFragmentFactory.QR_GENERATION_PROVIDER_TYPE, QrFragmentFactory.PLAIN_TEXT.ordinal())
                .putExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY, getUserInput());
    }

    @NonNull
    protected String getUserInput() {
        return userContent.getText().toString();
    }

    @Subscribe
    public void generateCodeButtonClicked(GenerateCodeButtonClickedEvent event) {
        attemptLaunchingQrCodeActivity();
    }

    protected void attemptLaunchingQrCodeActivity() {
        if (!TextUtils.isEmpty(getUserInput())) {
            launchActivityWithQrCode();
        } else {
            showError();
        }
    }
}
