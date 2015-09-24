package pl.touk.qrgen.generation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

public interface QrCodeGeneration {
    @Nullable View getGenerationFormAndBind(LayoutInflater inflater);
    @Nullable Intent getIntentToActivityWithTranslation(Context context);
    boolean isBound();
}
