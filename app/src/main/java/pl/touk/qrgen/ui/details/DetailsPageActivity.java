package pl.touk.qrgen.ui.details;

import android.content.Context;
import android.content.Intent;
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
import pl.touk.qrgen.ui.qrselect.LandingActivityComponentProvider;
import pl.touk.qrgen.ui.qrselect.LandingPagerAdapter;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;

public class DetailsPageActivity extends AppCompatActivity {

    public static final String TAG = "LandingPageActivity";
    @Bind(R.id.tool_bar) Toolbar toolbar;
//    @Inject FloatingActionButtonOverlay floatingActionButtonOverlay;

    public static Intent getIntent(Context context) {
        return new Intent(context, DetailsPageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

//        component = DaggerLandingActivityComponent.builder()
//                .qrGenComponent(QrGenApplication.component(this))
//                .landingActivityPagerAdapterModule(new LandingActivityPagerAdapterModule(this))
//                .build();
//        component.inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

//        floatingActionButtonOverlay.attach(this);
    }

//    public LandingActivityComponent getComponent() {
//        return component;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        bus.register(this);
//        floatingActionButtonOverlay.showButtonWithAnimation(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        bus.unregister(this);
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
    }
}
