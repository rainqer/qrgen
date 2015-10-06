package pl.rhinoonabus.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.details.DetailsPageActivity;

public abstract class QrCodeType {
    public static int QR_TYPE_CARD_RES_ID = R.layout.qr_code_type_card;
    abstract public int getSubTitleResId();
    abstract public int getTitleResId();
    abstract public int getDescriptionResId();
    abstract public int getDrawableResId();
    abstract public int getTransitionViewId();
    abstract public String getDrawableViewTransitionName();
    abstract Bundle provideTransitionOptions(AppCompatActivity activity, TextView animationStartPoint);
    abstract Intent prepareIntentForDetailsActivity(Intent intent);

    public void launchActivityWithDetailsForm(AppCompatActivity activity, TextView animationStartPoint) {
        activity.startActivity(
                prepareIntentForDetailsActivity(DetailsPageActivity.getIntent(activity)),
                provideTransitionOptions(activity, animationStartPoint));
    }
}
