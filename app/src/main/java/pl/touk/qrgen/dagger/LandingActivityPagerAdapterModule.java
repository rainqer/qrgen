package pl.touk.qrgen.dagger;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.ui.qrselect.LandingPageActivity;
import pl.touk.qrgen.ui.qrselect.LandingPagerAdapter;
import pl.touk.qrgen.ui.ResourceProvider;

@Module
public final class LandingActivityPagerAdapterModule {

    private final LandingPageActivity activity;

    public LandingActivityPagerAdapterModule(LandingPageActivity activity) {
        this.activity = activity;
    }

    @LandingActivityScope
    @Provides
    LandingPagerAdapter providesLandingPagerAdapter(ResourceProvider resourceProvider) {
        return new LandingPagerAdapter(
                activity.getSupportFragmentManager(),
                resourceProvider);
    }
}
