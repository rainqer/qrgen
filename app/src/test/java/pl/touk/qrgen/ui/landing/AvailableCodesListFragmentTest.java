package pl.touk.qrgen.ui.landing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
public class AvailableCodesListFragmentTest {

    AvailableCodesListFragment availableCodesListFragment = new AvailableCodesListFragment();

    @Before
    public void setUp() {
        startFragment(availableCodesListFragment);
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

//    @Test
//    public void shouldLaunchPlainTextGenerationDetailsActivityOnClick() {
//        // given
//        int plainTextPosition = 0;
//
//        // when
//        getViewItemFromListAdapter(plainTextPosition).performClick();
//
//        // then
////        assertThat(getNextStartedActivityIntent()
////                .getIntExtra(QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE, -1))
////                .isEqualTo(QrGenerationDetailsFormFactory.PLAIN_TEXT.ordinal());
//    }
//
//    private View getViewItemFromListAdapter(int position) {
//        return (View) availableCodesListFragment.availableTranslationsList.getItemAtPosition(position);
//    }
//
//    private Intent getNextStartedActivityIntent() {
//        ShadowActivity shadowActivity = shadowOf(availableCodesListFragment.getActivity());
//        return shadowActivity.getNextStartedActivity();
//    }
}