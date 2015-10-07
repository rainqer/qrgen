package pl.rhinoonabus.ui.qr;

import android.content.Intent;
import android.support.annotation.NonNull;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.ResourceProvider;
import pl.rhinoonabus.ui.details.QrGenerationDetailsFormFactory;

public class QrCodePlainTextType extends QrCodeType {

    private final String plainTextTransition;

    public QrCodePlainTextType(ResourceProvider resourceProvider) {
        plainTextTransition = resourceProvider.getString(R.string.translation_plain_text_transition);
    }

    @Override
    public int getSubTitleResId() {
        return R.string.translation_subtitle;
    }

    @Override
    public int getTitleResId() {
        return R.string.translation_plain_text_title;
    }

    @Override
    public int getDescriptionResId() {
        return R.string.translation_plain_text_content;
    }

    @Override
    public int getDrawableResId() {
        return R.drawable.ic_create_black_36dp;
    }

    @Override
    public int getTransitionViewId() {
        return R.id.card_plain_text_transition_view;
    }

    @NonNull
    @Override
    public String getTransitionName() {
        return plainTextTransition;
    }

    @NonNull
    @Override
    Intent prepareIntentForDetailsActivity(@NonNull Intent intent) {
        return intent.putExtra(QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE,
                QrGenerationDetailsFormFactory.PLAIN_TEXT.ordinal());
    }
}
