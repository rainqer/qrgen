package pl.touk.qrgen.dagger;

import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class MockBusModule {

    @Singleton
    @Provides
    Bus provideBus() {
        return mock(Bus.class);
    }
}
