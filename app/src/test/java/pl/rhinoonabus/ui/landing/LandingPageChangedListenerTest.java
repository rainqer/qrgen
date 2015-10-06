package pl.rhinoonabus.ui.landing;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;

import pl.rhinoonabus.events.GenerateCodePageSelectedEvent;
import pl.rhinoonabus.events.ScanCodePageSelectedEvent;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LandingPageChangedListenerTest {

    private LandingPageChangedListener landingPageChangedListener;
    private Bus bus;

    @Before
    //TODO figure out proper injection into test on this simple example
    public void setUp() {
        landingPageChangedListener = new LandingPageChangedListener();
        bus = mock(Bus.class);
        landingPageChangedListener.bus = bus;
    }

    @Test
    public void shouldPropagateChangedToGeneratePage() {
        // given
        int generateCodesPageIndex = 0;

        // when
        landingPageChangedListener.onPageSelected(generateCodesPageIndex);

        // then
        verify(bus).post(isA(GenerateCodePageSelectedEvent.class));
    }

    @Test
    public void shouldPropagateChangedToScanCodePaged() {
        // given
        int scanCodesPageIndex = 1;

        // when
        landingPageChangedListener.onPageSelected(scanCodesPageIndex);

        // then
        verify(bus).post(isA(ScanCodePageSelectedEvent.class));
    }
}