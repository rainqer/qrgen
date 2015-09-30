package pl.touk.qrgen.ui.landing;

import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowListView;

import pl.touk.qrgen.ui.details.QrGenerationDetailsFormFactory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startVisibleFragment;

@RunWith(RobolectricGradleTestRunner.class)
public class AvailableCodesListFragmentTest {

    AvailableCodesListFragment availableCodesListFragment;

    @Before
    public void setUp() {
        availableCodesListFragment = new AvailableCodesListFragment();
        startVisibleFragment(availableCodesListFragment,
                HollowLandingActivityWithComponent.class,
                android.R.id.content);
    }

    @Test
    public void shouldFreeClickListenerAfterOnDestroyView() {
        // expected
        assertThat(availableCodesListFragment.availableTranslationsList.getOnItemClickListener())
                .isNotNull();

        // when
        availableCodesListFragment.onDestroyView();

        // then
        assertThat(availableCodesListFragment.availableTranslationsList.getOnItemClickListener())
                .isNull();
    }

    @Test
    public void shouldLaunchPlainTextGenerationDetailsActivityOnClick() {
        // given
        int plainTextPosition = 0;

        // when
        ShadowListView shadowListView = shadowOf(availableCodesListFragment.availableTranslationsList);
        shadowListView.populateItems();
        shadowListView.performItemClick(plainTextPosition);

        // then
        assertThat(getNextStartedActivityIntent()
                .getIntExtra(QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE, -1))
                .isEqualTo(QrGenerationDetailsFormFactory.PLAIN_TEXT.ordinal());
    }

    @Test
    public void shouldLaunchUrlGenerationDetailsActivityOnClick() {
        // given
        int urlPosition = 1;

        // when
        ShadowListView shadowListView = shadowOf(availableCodesListFragment.availableTranslationsList);
        shadowListView.populateItems();
        shadowListView.performItemClick(urlPosition);

        // then
        assertThat(getNextStartedActivityIntent()
                .getIntExtra(QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE, -1))
                .isEqualTo(QrGenerationDetailsFormFactory.URL.ordinal());
    }

    @Test
    public void shouldLaunchPhoneGenerationDetailsActivityOnClick() {
        // given
        int phoneNumberPosition = 2;

        // when
        ShadowListView shadowListView = shadowOf(availableCodesListFragment.availableTranslationsList);
        shadowListView.populateItems();
        shadowListView.performItemClick(phoneNumberPosition);

        // then
        assertThat(getNextStartedActivityIntent()
                .getIntExtra(QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE, -1))
                .isEqualTo(QrGenerationDetailsFormFactory.PHONE.ordinal());
    }

    private Intent getNextStartedActivityIntent() {
        ShadowActivity shadowActivity = shadowOf(availableCodesListFragment.getActivity());
        return shadowActivity.getNextStartedActivity();
    }
}