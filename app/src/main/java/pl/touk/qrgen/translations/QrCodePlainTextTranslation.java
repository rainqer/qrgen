package pl.touk.qrgen.translations;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.generated.CodeGeneratedActivity;

public class QrCodePlainTextTranslation implements QrCodeTranslation {

    private static final int PLAIN_TEXT_TRANSLATION_CARD_RES_ID = R.layout.translation_card_plain_text;
    private View boundView;
    @Bind(R.id.translation_content) EditText editText;

    @NonNull
    @Override
    public View getTranslationViewAndBind(LayoutInflater inflater) {
        boundView = inflater.inflate(PLAIN_TEXT_TRANSLATION_CARD_RES_ID, null);
        ButterKnife.bind(this, boundView);
        return boundView;
    }

    @Nullable
    @Override
    public View getBoundView() {
        return boundView;
    }

    @Override
    public boolean isBound() {
        return boundView != null;
    }

    @Nullable
    @Override
    public Intent getIntentToActivityWithTranslation(Context context) {
        return editText != null
                ? CodeGeneratedActivity.getIntent(context)
                    .putExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY, editText.getText().toString())
                : null;
    }
}
