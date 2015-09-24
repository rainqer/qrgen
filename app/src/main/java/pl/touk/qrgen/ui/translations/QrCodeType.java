package pl.touk.qrgen.ui.translations;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import pl.touk.qrgen.R;

public interface QrCodeType {
    int QR_TYPE_CARD_RES_ID = R.layout.qr_code_type_card;
    int getSubTitleResId();
    int getTitleResId();
    int getDescriptionResId();
    int getDrawableResId();
    void launchActivityWithDetailsForm(AppCompatActivity activity, ImageView animationStartPoint);
}
