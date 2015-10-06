package pl.rhinoonabus.dagger;

import dagger.Component;
import pl.rhinoonabus.ui.landing.LandingPageActivity;
import pl.rhinoonabus.ui.landing.LandingPagerAdapter;
import pl.rhinoonabus.ui.landing.AvailableCodesListFragment;
import pl.rhinoonabus.ui.landing.ScanningFragment;

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
