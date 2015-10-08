package pl.rhinoonabus.ui.generated;

import android.content.Intent;
import android.support.annotation.NonNull;
import pl.rhinoonabus.service.QrCodeGenerator;
import pl.rhinoonabus.service.QrPhoneCodeGenerator;

public class PhoneQrFragment extends QrFragment {

    public static final String PHONE_NUMBER_PREFIX = "tel:";

    @NonNull
    @Override
    protected String extractDataFromIntent() {
        String phoneNumber = getPhoneNumberFromIntent();
        return phoneNumber.startsWith(PHONE_NUMBER_PREFIX)
                ? phoneNumber.replace(PHONE_NUMBER_PREFIX, "")
                : phoneNumber;
    }

    private String getPhoneNumberFromIntent() {
        String phoneNumber = getActivity().getIntent()
                    .getStringExtra(Intent.EXTRA_TEXT);
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
