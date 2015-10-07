package pl.rhinoonabus.ui.qr;

import android.content.Intent;
import android.support.annotation.NonNull;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.ResourceProvider;
import pl.rhinoonabus.ui.details.QrGenerationDetailsFormFactory;

public class QrCodePhoneType extends QrCodeType {

    private final String phoneTransition;

    public QrCodePhoneType(ResourceProvider resourceProvider) {
        phoneTransition = resourceProvider.getString(R.string.translation_phone_transition);
    }

    @Override
    public int getSubTitleResId() {
        return R.string.translation_subtitle;
    }

    @Override
    public int getTitleResId() {
        return R.string.translation_phone_title;
    }

    @Override
    public int getDescriptionResId() {
        return R.string.translation_phone_content;
    }

    @Override
    public int getDrawableResId() {
        return R.drawable.ic_phone_black_36dp;
    }

    @Override
    public int getTransitionViewId() {
        return R.id.card_phone_transition_view;
    }

    @NonNull
    @Override
    public String getTransitionName() {
        return phoneTransition;
    }

    @NonNull
    @Override
    Intent prepareIntentForDetailsActivity(@NonNull Intent intent) {
        return intent.putExtra(
                QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE,
                QrGenerationDetailsFormFactory.PHONE.ordinal());
    }
}
