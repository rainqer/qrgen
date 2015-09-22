package pl.touk.qrgen.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.QrGenApplication;
import pl.touk.qrgen.R;
import pl.touk.qrgen.dagger.DaggerLandingActivityComponent;
import pl.touk.qrgen.dagger.LandingActivityComponent;
import pl.touk.qrgen.dagger.LandingActivityPagerAdapterModule;
import pl.touk.qrgen.events.GenerateCodePageSelectedEvent;
import pl.touk.qrgen.events.ScanCodePageSelectedEvent;
import pl.touk.qrgen.ui.common.LandingPageChangedListener;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;

public class LandingPageActivity extends AppCompatActivity {

    @Bind(R.id.pager)ViewPager viewPager;
    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.tool_bar) Toolbar toolbar;
    @Inject FloatingActionButtonOverlay floatingActionButtonOverlay;
    @Inject LandingPageChangedListener landingPageChangedListener;
    @Inject Bus bus;
    @Inject LandingPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        LandingActivityComponent component = DaggerLandingActivityComponent.builder()
                .qrGenComponent(QrGenApplication.component(this))
                .landingActivityPagerAdapterModule(new LandingActivityPagerAdapterModule(this))
                .build();
        component.inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        floatingActionButtonOverlay.attach(this);
        setupPager();
    }

    private void setupPager() {
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(landingPageChangedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
        floatingActionButtonOverlay.showButtonWithAnimation(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
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
        ButterKnife.unbind(mSectionsPagerAdapter);
        viewPager.clearOnPageChangeListeners();
    }

    @Subscribe
    public void onGenerateCodePageSelected(GenerateCodePageSelectedEvent event) {
        floatingActionButtonOverlay.showButtonWithAnimation(this);
    }

    @Subscribe
    public void onGenerateCodePageSelected(ScanCodePageSelectedEvent event) {
        floatingActionButtonOverlay.hideButtonWithAnimation(this);
    }
}
