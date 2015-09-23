package pl.touk.qrgen.dagger;

import dagger.Component;
import pl.touk.qrgen.ui.qrselect.LandingPageActivity;
import pl.touk.qrgen.ui.qrselect.LandingPagerAdapter;
import pl.touk.qrgen.ui.qrselect.AvailableCodesListFragment;
import pl.touk.qrgen.ui.scaning.ScanningFragment;

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
