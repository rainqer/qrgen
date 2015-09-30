package pl.touk.qrgen.dagger;

import android.app.Activity;
import android.support.annotation.NonNull;

public class Components {

    private Components() {
        throw new AssertionError("No instances.");
    }

    public static <T> T from(@NonNull Activity activity) {
        return ((HasComponent<T>) activity).getComponent();
    }
}
