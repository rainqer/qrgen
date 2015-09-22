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
        @LandingActivityScope LandingPagerAdapter providesLandingPagerAdapter();
        void inject(LandingPageActivity landingPageActivity);
        void inject(AvailableCodesListFragment availableCodesListFragment);
        void inject(ScanningFragment scanningFragment);
}
