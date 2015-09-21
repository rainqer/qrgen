package pl.touk.qrgen.dagger;

import javax.inject.Singleton;
import dagger.Component;
import pl.touk.qrgen.ui.LandingPageActivity;
import pl.touk.qrgen.ui.common.LandingPageChangedListener;

@Singleton
@Component (
        modules = {
                EventBusModule.class,
                InterfaceComponentsModule.class
        }
)
public interface QrGenComponent {
        void inject(LandingPageChangedListener landingPageChangedListener);
        void inject(LandingPageActivity landingPageActivity);
        LandingPageChangedListener getLandingPageChangedListener();
}
