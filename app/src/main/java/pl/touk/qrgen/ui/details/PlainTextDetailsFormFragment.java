package pl.touk.qrgen.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.generated.CodeGeneratedActivity;

public class PlainTextDetailsFormFragment extends QrGenerationDetailsForm {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.translation_details_plain_text, null);
    }

    @Nullable
    @Override
    public Intent getIntentToActivityWithGeneratedQrCode() {
        return CodeGeneratedActivity.getIntent(getActivity()).putExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY, "AAA");
    }
}
