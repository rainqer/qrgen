package pl.touk.qrgen.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.QrGenApplication;

@Module
public class ContextModule {

    private final QrGenApplication application;

    public ContextModule(QrGenApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }
}
