package pl.touk.qrgen.translations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

public interface QrCodeTranslation {
    @NonNull View getTranslationViewAndBind(LayoutInflater inflater);
    @Nullable View getBoundView();
    @Nullable Intent getIntentToActivityWithTranslation(Context context);
    void launchActivityWithDetails(AppCompatActivity activity);
    boolean isBound();
}
