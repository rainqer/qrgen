package pl.touk.qrgen.dagger;

import dagger.Component;
import pl.touk.qrgen.ui.LandingPageActivity;
import pl.touk.qrgen.ui.LandingPagerAdapter;
import pl.touk.qrgen.ui.common.LandingPageChangedListener;
import pl.touk.qrgen.ui.generation.AvailableCodeTranslationsListAdapter;
import pl.touk.qrgen.ui.generation.AvailableCodesListFragment;
import pl.touk.qrgen.ui.scaning.ScanningFragment;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;

@LandingActivityScope
@Component (
        dependencies = {
                QrGenComponent.class
        },
        modules = {
                LandingActivityPagerAdapterModule.class
        }
)
public interface LandingActivityComponent extends QrGenComponent {
//        void inject(LandingPageChangedListener landingPageChangedListener);
//        void inject(LandingPagerAdapter landingPageActivity);
        void inject(LandingPageActivity landingPageActivity);
//        void inject(AvailableCodeTranslationsListAdapter availableCodeTranslationsListAdapter);
//        void inject(AvailableCodesListFragment availableCodesListFragment);
//        void inject(ScanningFragment scanningFragment);
//        void inject(FloatingActionButtonOverlay floatingActionButtonOverlay);
}
