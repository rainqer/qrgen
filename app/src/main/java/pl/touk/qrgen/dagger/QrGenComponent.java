package pl.touk.qrgen.dagger;

import javax.inject.Singleton;

import dagger.Component;
import pl.touk.qrgen.QrGenApplication;

@Singleton
@Component (
        modules = {
                EventBusModule.class,
                ContextModule.class,
        }
)
public interface QrGenComponent {
        void inject(QrGenApplication application);
}
