package pl.touk.qrgen.ui.translations;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormProviderFactory;
import pl.touk.qrgen.ui.details.DetailsPageActivity;

public class QrCodePlainTextType implements QrCodeType {

    private final String plainTextTransition;

    public QrCodePlainTextType(ResourceProvider resourceProvider) {
        plainTextTransition = resourceProvider.getString(R.string.translation_plain_transition);
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
    public void launchActivityWithDetailsForm(AppCompatActivity activity, ImageView animationStartPoint) {
        Bundle options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        animationStartPoint,
                        plainTextTransition
                ).toBundle();
        options.putInt(QrGenerationDetailsFormProviderFactory.QR_GENERATION_PROVIDER_TYPE, QrGenerationDetailsFormProviderFactory.PLAIN_TEXT.ordinal());
        activity.startActivity(DetailsPageActivity.getIntent(activity), options);
    }
}
