package pl.touk.qrgen.generation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.generated.CodeGeneratedActivity;

public class PlainTextGeneration implements QrCodeGeneration {

    @Nullable
    @Override
    public View getGenerationFormAndBind(LayoutInflater inflater) {
        return inflater.inflate(R.layout.translation_details_plain_text, null);
    }

    @Nullable
    @Override
    public Intent getIntentToActivityWithTranslation(Context context) {
        return CodeGeneratedActivity.getIntent(context).putExtra(CodeGeneratedActivity.TRANSLATION_CONTENT_KEY, "AAA");
    }

    @Override
    public boolean isBound() {
        return false;
    }

}
