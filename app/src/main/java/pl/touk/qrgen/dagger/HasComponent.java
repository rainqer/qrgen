package pl.touk.qrgen.dagger;

import android.support.annotation.NonNull;

public interface HasComponent<T> {
    @NonNull T getComponent();
}
