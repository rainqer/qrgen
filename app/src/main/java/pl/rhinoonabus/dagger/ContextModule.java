package pl.rhinoonabus.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.rhinoonabus.QrGenApplication;

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
