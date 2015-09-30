package pl.touk.qrgen.ui.landing;

import android.support.v4.view.ViewPager;
import android.view.View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
public class LandingPageActivityTest {

    private LandingPageActivity landingPageActivity = buildActivity();

    @Test
    public void shouldDisplayToolBar() {
        // when
        View toolbar = ButterKnife.findById(landingPageActivity, R.id.tool_bar);

        // then
        assertThat(toolbar).isNotNull();
    }

    @Test
    public void shouldRemoveListenersAfterDestroyingViews() {
        // given
        ViewPager viewPager = mock(ViewPager.class);
        landingPageActivity.viewPager = viewPager;

        // when
        landingPageActivity.onDestroy();

        // then
        verify(viewPager).clearOnPageChangeListeners();
    }

    private LandingPageActivity buildActivity() {
        return Robolectric.buildActivity(LandingPageActivity.class).create().start().get();
    }
}