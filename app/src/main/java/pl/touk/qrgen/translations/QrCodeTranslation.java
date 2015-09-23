package pl.touk.qrgen.translations;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

public interface QrCodeTranslation {
    @NonNull View getTranslationViewAndBind(LayoutInflater inflater);
    @Nullable View getBoundView();
    @Nullable Intent getIntentToActivityWithTranslation(Context context);
    boolean isBound();
}
