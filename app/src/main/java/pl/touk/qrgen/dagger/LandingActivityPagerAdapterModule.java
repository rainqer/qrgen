package pl.touk.qrgen.dagger;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.ui.LandingPageActivity;
import pl.touk.qrgen.ui.LandingPageFragmentWithTitle;
import pl.touk.qrgen.ui.LandingPagerAdapter;
import pl.touk.qrgen.ui.ResourceProvider;

@Module
public class LandingActivityPagerAdapterModule {

    @LandingActivityScope
    @Provides
    FragmentManager providesFragmentManager(LandingPageActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @LandingActivityScope
    @Provides
    LandingPagerAdapter providesLandingPagerAdapter(FragmentManager fragmentManager,
                                                    ResourceProvider resourceProvider,
                                                    LandingPageFragmentWithTitle...fragmentWithTitles) {
        return new LandingPagerAdapter(fragmentManager, resourceProvider, fragmentWithTitles);
    }
}
