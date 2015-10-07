package pl.rhinoonabus;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import pl.rhinoonabus.dagger.ContextModule;
import pl.rhinoonabus.dagger.DaggerQrGenComponent;
import pl.rhinoonabus.dagger.EventBusModule;
import pl.rhinoonabus.dagger.QrGenComponent;
import pl.rhinoonabus.qrgen.BuildConfig;

public class QrGenApplication extends Application {

    private QrGenComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        loadCrashlytics();
        assembleDaggerComponent();
    }

    private void loadCrashlytics() {
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }

    private void assembleDaggerComponent() {
        component = DaggerQrGenComponent.builder()
                .eventBusModule(new EventBusModule())
                .contextModule(new ContextModule(QrGenApplication.this))
                .build();
        component.inject(this);
    }

    public static QrGenComponent component(Context context) {
        return ((QrGenApplication) context.getApplicationContext()).component;
    }
}