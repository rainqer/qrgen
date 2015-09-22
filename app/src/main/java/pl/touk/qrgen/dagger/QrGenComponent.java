package pl.touk.qrgen.dagger;

import javax.inject.Singleton;
import dagger.Component;
import pl.touk.qrgen.QrGenApplication;
import pl.touk.qrgen.ui.LandingPageActivity;
import pl.touk.qrgen.ui.LandingPagerAdapter;
import pl.touk.qrgen.ui.common.LandingPageChangedListener;
import pl.touk.qrgen.ui.generation.AvailableCodeTranslationsListAdapter;
import pl.touk.qrgen.ui.generation.AvailableCodesListFragment;
import pl.touk.qrgen.ui.scaning.ScanningFragment;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;

@Singleton
@Component (
        modules = {
                EventBusModule.class,
                ContextModule.class,
                LandingActivityModule.class
        }
)
public interface QrGenComponent {
        void inject(QrGenApplication application);
        void inject(LandingPageChangedListener landingPageChangedListener);
        void inject(LandingPagerAdapter landingPageActivity);
        void inject(LandingPageActivity landingPageActivity);
        void inject(AvailableCodeTranslationsListAdapter availableCodeTranslationsListAdapter);
        void inject(AvailableCodesListFragment availableCodesListFragment);
        void inject(ScanningFragment scanningFragment);
        void inject(FloatingActionButtonOverlay floatingActionButtonOverlay);
}
