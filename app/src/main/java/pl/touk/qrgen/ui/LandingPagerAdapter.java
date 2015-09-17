package pl.touk.qrgen.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import butterknife.BindString;
import pl.touk.qrgen.R;

public class LandingPagerAdapter extends FragmentPagerAdapter {

    @BindString(R.string.title_section1) String titlePage1;
    @BindString(R.string.title_section2) String titlePage2;

    public LandingPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
    }

    @Override
    public Fragment getItem(int position) {
        return QrCreatorFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return titlePage1;
            case 1:
                return titlePage2;
        }
        return null;
    }
}
