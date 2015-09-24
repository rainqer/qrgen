package pl.touk.qrgen.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormProviderFactory;

public class QrCodeUrlType extends QrCodeType {

    private final String urlTransition;

    public QrCodeUrlType(ResourceProvider resourceProvider) {
        urlTransition = resourceProvider.getString(R.string.translation_url_transition);
    }

    @Override
    public int getSubTitleResId() {
        return R.string.translation_subtitle;
    }

    @Override
    public int getTitleResId() {
        return R.string.translation_url_title;
    }

    @Override
    public int getDescriptionResId() {
        return R.string.translation_url_content;
    }

    @Override
    public int getDrawableResId() {
        return R.drawable.ic_link_black_36dp;
    }

    @Override
    public int getTransitionViewId() {
        return R.id.card_url_transition_view;
    }

    @Override
    public String getDrawableViewTransitionName() {
        return urlTransition;
    }

    @Override
    Bundle provideTransitionOptions(AppCompatActivity activity, TextView animationStartPoint) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                animationStartPoint,
                urlTransition
        ).toBundle();
    }

    @Override
    Intent prepareIntentForDetailsActivity(Intent intent) {
        return intent.putExtra(
                QrGenerationDetailsFormProviderFactory.QR_GENERATION_PROVIDER_TYPE,
                QrGenerationDetailsFormProviderFactory.URL.ordinal());
    }
}
