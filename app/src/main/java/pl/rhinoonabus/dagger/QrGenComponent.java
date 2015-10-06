package pl.rhinoonabus.dagger;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import pl.rhinoonabus.QrGenApplication;

@Singleton
@Component (
        modules = {
                EventBusModule.class,
                ContextModule.class,
        }
)
public interface QrGenComponent {
        Bus bus();
        @ForApplication Context getContext();
        void inject(QrGenApplication application);
}
