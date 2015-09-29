package pl.touk.qrgen.ui.landing;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.ResourceProvider;

public class LandingPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public LandingPagerAdapter(@NonNull FragmentManager fragmentManager,
                               @NonNull ResourceProvider resourceProvider) {
        super(fragmentManager);
        initTitles(resourceProvider);
    }

    private void initTitles(ResourceProvider resourceProvider) {
        titles = new String[] {
                resourceProvider.getString(R.string.title_section1),
                resourceProvider.getString(R.string.title_section2)
        };
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0 :
                fragment = new AvailableCodesListFragment();
                break;
            case 1 :
                fragment = new ScanningFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return inRange(position)
                ? titles[position]
                : null;
    }

    private boolean inRange(int position) {
        return position < titles.length;
    }
}
