package pl.rhinoonabus.dagger;

import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class EventBusModule {

    @Singleton
    @Provides
    Bus provideBus() {
        return new Bus();
    }
}
