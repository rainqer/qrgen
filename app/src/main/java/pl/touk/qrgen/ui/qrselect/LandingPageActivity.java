package pl.touk.qrgen.ui.qrselect;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.QrGenApplication;
import pl.touk.qrgen.R;
import pl.touk.qrgen.dagger.DaggerLandingActivityComponent;
import pl.touk.qrgen.dagger.LandingActivityComponent;
import pl.touk.qrgen.dagger.LandingActivityPagerAdapterModule;
import pl.touk.qrgen.ui.common.LandingPageChangedListener;

public class LandingPageActivity extends AppCompatActivity implements LandingActivityComponentProvider {

    public static final String TAG = "LandingPageActivity";
    @Bind(R.id.pager) ViewPager viewPager;
    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.tool_bar) Toolbar toolbar;
    @Inject LandingPageChangedListener landingPageChangedListener;
    @Inject LandingPagerAdapter sectionsPagerAdapter;
    private LandingActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        assembleDaggerComponent();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupPager();
    }

    private void assembleDaggerComponent() {
        component = DaggerLandingActivityComponent.builder()
                .qrGenComponent(QrGenApplication.component(this))
                .landingActivityPagerAdapterModule(new LandingActivityPagerAdapterModule(this))
                .build();
        component.inject(this);
    }

    public LandingActivityComponent getComponent() {
        return component;
    }

    private void setupPager() {
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(landingPageChangedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(sectionsPagerAdapter);
        viewPager.clearOnPageChangeListeners();
    }
}
