package pl.touk.qrgen;

import android.app.Application;
import android.content.Context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.touk.qrgen.dagger.DaggerQrGenComponent;
import pl.touk.qrgen.dagger.EventBusModule;
import pl.touk.qrgen.dagger.QrGenComponent;

public class QrGenApplication extends Application {

    private QrGenComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        provideDependencyComponent();
    }

    public static QrGenComponent component(Context context) {
        return ((QrGenApplication) context.getApplicationContext()).component;
    }

    private void provideDependencyComponent() {
        component = DaggerInitializer.init();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private final static class DaggerInitializer {
        private static QrGenComponent init() {
            return DaggerQrGenComponent.builder()
                    .eventBusModule(new EventBusModule())
                    .build();
        }
    }
}