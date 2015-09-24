package pl.touk.qrgen.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.details.DetailsPageActivity;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormProviderFactory;

public abstract class QrCodeType {
    public static int QR_TYPE_CARD_RES_ID = R.layout.qr_code_type_card;
    abstract public int getSubTitleResId();
    abstract public int getTitleResId();
    abstract public int getDescriptionResId();
    abstract public int getDrawableResId();
    abstract public int getDrawableViewId();
    abstract Bundle provideTransitionOptions(AppCompatActivity activity, ImageView animationStartPoint);
    abstract Intent prepareIntentForDetailsActivity(Intent intent);

    public void launchActivityWithDetailsForm(AppCompatActivity activity, ImageView animationStartPoint) {
        activity.startActivity(
                prepareIntentForDetailsActivity(DetailsPageActivity.getIntent(activity)),
                provideTransitionOptions(activity,animationStartPoint));
    }
}
