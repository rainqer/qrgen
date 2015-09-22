package pl.touk.qrgen.ui;

import android.content.Context;
import javax.inject.Inject;

import pl.touk.qrgen.dagger.ForApplication;

public class ResourceProvider {

    private final Context context;

    @Inject
    public ResourceProvider(@ForApplication Context context) {
        this.context = context;
    }

    public int getColor(int colorResId) {
        return context.getColor(colorResId);
    }
}
