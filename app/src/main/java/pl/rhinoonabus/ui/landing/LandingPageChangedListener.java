package pl.rhinoonabus.ui.landing;

import android.support.v4.view.ViewPager;
import com.squareup.otto.Bus;
import javax.inject.Inject;
import pl.rhinoonabus.events.GenerateCodePageSelectedEvent;
import pl.rhinoonabus.events.QrGenPagerEvent;
import pl.rhinoonabus.events.ScanCodePageSelectedEvent;

public class LandingPageChangedListener implements ViewPager.OnPageChangeListener {

    @Inject Bus bus;

    @Inject
    public LandingPageChangedListener() {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bus.post(getPageChangedEvent(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private QrGenPagerEvent getPageChangedEvent(int position) {
        return position == 0 ? new GenerateCodePageSelectedEvent() : new ScanCodePageSelectedEvent();
    }
}
