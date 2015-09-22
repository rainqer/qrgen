package pl.touk.qrgen.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import javax.inject.Inject;
import pl.touk.qrgen.dagger.ForApplication;

public class ResourceProvider {

    private final Context context;

    @Inject
    public ResourceProvider(@ForApplication Context context) {
        this.context = context;
    }

    public int getColor(int colorResId) {
        return ContextCompat.getColor(context, colorResId);
    }

    public String getString(int stringResId) {
        return context.getString(stringResId);
    }
}
