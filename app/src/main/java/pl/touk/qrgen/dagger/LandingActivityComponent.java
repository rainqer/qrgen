package pl.touk.qrgen.dagger;

import dagger.Component;
import pl.touk.qrgen.ui.landing.LandingPageActivity;
import pl.touk.qrgen.ui.landing.LandingPagerAdapter;
import pl.touk.qrgen.ui.landing.AvailableCodesListFragment;
import pl.touk.qrgen.ui.landing.ScanningFragment;

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
        @LandingActivityScope LandingPagerAdapter providesLandingPagerAdapter();
        void inject(LandingPageActivity landingPageActivity);
        void inject(AvailableCodesListFragment availableCodesListFragment);
        void inject(ScanningFragment scanningFragment);
}
