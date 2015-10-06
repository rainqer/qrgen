package pl.rhinoonabus.dagger;

import android.support.annotation.NonNull;

public interface HasComponent<T> {
    @NonNull T getComponent();
}
