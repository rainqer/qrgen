package pl.touk.qrgen.dagger;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.ui.LandingPageActivity;
import pl.touk.qrgen.ui.LandingPagerAdapter;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.generation.AvailableCodesListFragment;
import pl.touk.qrgen.ui.scaning.ScanningFragment;

@Module
public final class LandingActivityPagerAdapterModule {

    private final LandingPageActivity activity;

    public LandingActivityPagerAdapterModule(LandingPageActivity activity) {
        this.activity = activity;
    }

    @LandingActivityScope
    @Provides
    LandingPagerAdapter providesLandingPagerAdapter(ResourceProvider resourceProvider,
                                                    AvailableCodesListFragment availableCodesListFragment,
                                                    ScanningFragment scanningFragment) {
        return new LandingPagerAdapter(
                activity.getSupportFragmentManager(),
                resourceProvider,
                availableCodesListFragment,
                scanningFragment);
    }
}
