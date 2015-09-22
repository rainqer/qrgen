package pl.touk.qrgen.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import pl.touk.qrgen.ui.generation.AvailableCodesListFragment;
import pl.touk.qrgen.ui.scaning.ScanningFragment;

public class LandingPagerAdapter extends FragmentPagerAdapter {

    private final LandingPageFragmentWithTitle[] fragments;
//    = {
//            new AvailableCodesListFragment(),
//            new ScanningFragment()
//    };

    private String[] titles;

    public LandingPagerAdapter(@NonNull FragmentActivity activity, LandingPageFragmentWithTitle...fragments) {
        super(activity.getSupportFragmentManager());
        this.fragments = fragments;
        initTitles(activity);
    }

    private void initTitles(Context context) {
        List<String> listOfTitles = new ArrayList<>();
        for (LandingPageFragmentWithTitle fragmentWithTitle : fragments) {
            listOfTitles.add(context.getResources().getString(fragmentWithTitle.getTitleResId()));
        }
        titles = listOfTitles.toArray(new String[fragments.length]);
    }

    @Override
    public Fragment getItem(int position) {
        return inRange(position)
                ? fragments[position]
                : null;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return inRange(position)
                ? titles[position]
                : null;
    }

    private boolean inRange(int position) {
        return position < fragments.length;
    }
}
