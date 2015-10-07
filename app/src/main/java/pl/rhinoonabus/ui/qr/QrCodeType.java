package pl.rhinoonabus.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.details.DetailsPageActivity;

public abstract class QrCodeType {

    public static int QR_TYPE_CARD_RES_ID = R.layout.qr_code_type_card;

    @NonNull
    public abstract String getTransitionName();
    public abstract int getSubTitleResId();
    public abstract int getTitleResId();
    public abstract int getDescriptionResId();
    public abstract int getDrawableResId();
    public abstract int getTransitionViewId();

    @NonNull
    abstract Intent prepareIntentForDetailsActivity(@NonNull Intent intent);

    public void launchActivityWithDetailsForm(AppCompatActivity activity, TextView animationStartPoint) {
        activity.startActivity(
                prepareIntentForDetailsActivity(DetailsPageActivity.getIntent(activity)),
                provideTransitionOptions(activity, animationStartPoint));
    }

    private Bundle provideTransitionOptions(AppCompatActivity activity, TextView animationStartPoint) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                animationStartPoint,
                getTransitionName()
        ).toBundle();
    }
}
