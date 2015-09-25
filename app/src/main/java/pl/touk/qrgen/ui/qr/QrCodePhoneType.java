package pl.touk.qrgen.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormFactory;

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

    @Override
    public String getDrawableViewTransitionName() {
        return phoneTransition;
    }

    @Override
    Bundle provideTransitionOptions(AppCompatActivity activity, TextView animationStartPoint) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                animationStartPoint,
                phoneTransition
        ).toBundle();
    }

    @Override
    Intent prepareIntentForDetailsActivity(Intent intent) {
        return intent.putExtra(
                QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE,
                QrGenerationDetailsFormFactory.PHONE.ordinal());
    }
}
