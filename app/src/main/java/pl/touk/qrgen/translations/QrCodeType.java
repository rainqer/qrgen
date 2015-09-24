package pl.touk.qrgen.translations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

public interface QrCodeType {
    @NonNull View getTypeSelectionViewAndBind(LayoutInflater inflater);
    @Nullable View getBoundSelectionView();
    void launchActivityWithDetailsForm(AppCompatActivity activity);
    boolean isBound();
}
