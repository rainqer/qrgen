package pl.touk.qrgen;

import android.app.Application;
import android.content.Context;
import pl.touk.qrgen.dagger.ContextModule;
import pl.touk.qrgen.dagger.DaggerQrGenComponent;
import pl.touk.qrgen.dagger.EventBusModule;
import pl.touk.qrgen.dagger.QrGenComponent;

public class QrGenApplication extends Application {

    private QrGenComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
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