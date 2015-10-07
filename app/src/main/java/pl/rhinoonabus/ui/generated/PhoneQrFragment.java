package pl.rhinoonabus.ui.generated;

import android.support.annotation.NonNull;
import pl.rhinoonabus.service.QrCodeGenerator;
import pl.rhinoonabus.service.QrPhoneCodeGenerator;

public class PhoneQrFragment extends QrFragment {

    private static final String PREFIX = "tel:";

    @NonNull
    @Override
    protected String extractDataFromIntent() {
        String phoneNumber = getPhoneNumberFromIntent();
        return phoneNumber.startsWith(PREFIX)
                ? phoneNumber.replace(PREFIX, "")
                : phoneNumber;
    }

    private String getPhoneNumberFromIntent() {
        String phoneNumber = getActivity().getIntent()
                    .getStringExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY);
        return phoneNumber != null
                ? phoneNumber
                : "";
    }

    @Override
    protected boolean shouldShowLaunchLinkButton() {
        return false;
    }

    @Override
    protected boolean shouldShowDialButton() {
        return true;
    }

    @NonNull
    @Override
    protected QrCodeGenerator getQrCodeGenerator() {
        return new QrPhoneCodeGenerator();
    }
}
