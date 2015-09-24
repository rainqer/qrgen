package pl.touk.qrgen.translations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.generation.Generation;
import pl.touk.qrgen.ui.details.DetailsPageActivity;

public class QrCodePlainTextType implements QrCodeType {

    private static final int PLAIN_TEXT_TRANSLATION_CARD_RES_ID = R.layout.translation_card_plain_text;
    private View boundView;
    @Bind(R.id.plain_text_image) ImageView plainTextImage;
    @BindString(R.string.translation_plain_transition) String plainTextTransition;

    @NonNull
    @Override
    public View getTypeSelectionViewAndBind(LayoutInflater inflater) {
        boundView = inflater.inflate(PLAIN_TEXT_TRANSLATION_CARD_RES_ID, null);
        ButterKnife.bind(this, boundView);
        return boundView;
    }

    @Nullable
    @Override
    public View getBoundSelectionView() {
        return boundView;
    }

    @Override
    public boolean isBound() {
        return boundView != null;
    }

    @Override
    public void launchActivityWithDetailsForm(AppCompatActivity activity) {
        Bundle options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        plainTextImage,
                        plainTextTransition
                ).toBundle();
        options.putInt(Generation.QR_GENERATION_TYPE, Generation.PLAIN_TEXT.ordinal());
        activity.startActivity(DetailsPageActivity.getIntent(activity), options);
        unbind();
    }

    private void unbind() {
        ButterKnife.unbind(this);
        boundView = null;
    }
}
