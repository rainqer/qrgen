package pl.touk.qrgen.dagger;

import android.support.v4.app.FragmentManager;

import dagger.Component;
import pl.touk.qrgen.QrGenApplication;
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
//        @LandingActivityScope FragmentManager providesFragmentManager();
//        @LandingActivityScope LandingPagerAdapter providesLandingPagerAdapter();
        void inject(QrGenApplication application);
        void inject(LandingPageActivity landingPageActivity);
//        void inject(AvailableCodeTranslationsListAdapter availableCodeTranslationsListAdapter);
//        void inject(AvailableCodesListFragment availableCodesListFragment);
//        void inject(ScanningFragment scanningFragment);
//        void inject(FloatingActionButtonOverlay floatingActionButtonOverlay);
}
