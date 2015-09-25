package pl.touk.qrgen.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormFactory;

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

    @Override
    public String getDrawableViewTransitionName() {
        return plainTextTransition;
    }

    @Override
    Bundle provideTransitionOptions(AppCompatActivity activity, TextView animationStartPoint) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                animationStartPoint,
                plainTextTransition
        ).toBundle();
    }

    @Override
    Intent prepareIntentForDetailsActivity(Intent intent) {
        return intent.putExtra(QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE,
                QrGenerationDetailsFormFactory.PLAIN_TEXT.ordinal());
    }
}
