package pl.rhinoonabus.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.rhinoonabus.QrGenApplication;
import pl.rhinoonabus.dagger.DaggerDetailsActivityComponent;
import pl.rhinoonabus.dagger.DetailsActivityComponent;
import pl.rhinoonabus.dagger.DetailsActivityQrCodeGenerationModule;
import pl.rhinoonabus.dagger.HasComponent;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.VersionUtil;
import pl.rhinoonabus.ui.view.FloatingActionButtonOverlay;

public class DetailsPageActivity extends AppCompatActivity
        implements HasComponent<DetailsActivityComponent> {

    public static final String TAG = "LandingPageActivity";
    @Bind(R.id.tool_bar) Toolbar toolbar;
    @Inject FloatingActionButtonOverlay floatingActionButtonOverlay;
    @Inject QrGenerationDetailsFormFactory qrGenerationDetailsFormFactory;
    private DetailsActivityComponent component;

    public static Intent getIntent(Context context) {
        return new Intent(context, DetailsPageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doNotAnimateStatusBar();
        setContentView(R.layout.activity_details);
        assembleDaggerComponent();
        setUpUi();
    }

    private void doNotAnimateStatusBar() {
        if (VersionUtil.lolipopTransitionsAvailable()) {
            Transition fade = new Fade();
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(R.id.tool_bar, true);
            getWindow().setExitTransition(fade);
            getWindow().setEnterTransition(fade);
        }
    }

    private void assembleDaggerComponent() {
        component = DaggerDetailsActivityComponent.builder()
                .qrGenComponent(QrGenApplication.component(this))
                .detailsActivityQrCodeGenerationModule(
                        new DetailsActivityQrCodeGenerationModule(extractQrGenerationTypeFromIntent())
                )
                .build();
        component.inject(this);
    }

    private QrGenerationDetailsFormFactory extractQrGenerationTypeFromIntent() {
        int ordinal = getIntent().getIntExtra(
                QrGenerationDetailsFormFactory.QR_GENERATION_PROVIDER_TYPE,
                QrGenerationDetailsFormFactory.DEFAULT);
        return QrGenerationDetailsFormFactory.values()[ordinal];
    }

    private void setUpUi() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        floatingActionButtonOverlay.attach(this);
        addDetailsFormFragment();
    }

    private void addDetailsFormFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragment_container,
                        qrGenerationDetailsFormFactory.get()
                ).commit();
    }

    @Override
    public DetailsActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onResume() {
        super.onResume();
        floatingActionButtonOverlay.showButtonWithAnimation(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        floatingActionButtonOverlay.hideButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
