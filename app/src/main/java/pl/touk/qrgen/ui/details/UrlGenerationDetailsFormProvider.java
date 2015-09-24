package pl.touk.qrgen.ui.details;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class UrlGenerationDetailsFormProvider implements QrGenerationDetailsFormProvider {

    @NonNull
    @Override
    public Fragment getGenerationFormFragment() {
        return new UrlDetailsFormFragment();
    }
}
