package pl.touk.qrgen.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.landing.LandingPagerAdapter;

@Module
public final class LandingActivityPagerAdapterModule {

    private final AppCompatActivity activity;

    public LandingActivityPagerAdapterModule(AppCompatActivity activity) {
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
