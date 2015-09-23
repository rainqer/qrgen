package pl.touk.qrgen.ui.qrselect;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

        component = DaggerLandingActivityComponent.builder()
                .qrGenComponent(QrGenApplication.component(this))
                .landingActivityPagerAdapterModule(new LandingActivityPagerAdapterModule(this))
                .build();
        component.inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupPager();
    }

    public LandingActivityComponent getComponent() {
        return component;
    }

    private void setupPager() {
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.postInvalidate();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(landingPageChangedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(sectionsPagerAdapter);
        viewPager.clearOnPageChangeListeners();
    }
}
