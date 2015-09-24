package pl.touk.qrgen.ui.details;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface QrGenerationDetailsFormProvider {
    @NonNull Fragment getGenerationFormFragment();
}
