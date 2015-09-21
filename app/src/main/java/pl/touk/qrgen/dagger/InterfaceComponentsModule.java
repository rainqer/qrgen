package pl.touk.qrgen.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;

@Module
public class InterfaceComponentsModule {

    @Provides
    FloatingActionButtonOverlay provideFloatingActionButtonOverlay() {
        return new FloatingActionButtonOverlay();
    }
}
