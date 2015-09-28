package pl.touk.qrgen.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.QrGenApplication;
import pl.touk.qrgen.R;
import pl.touk.qrgen.dagger.DaggerDetailsActivityComponent;
import pl.touk.qrgen.dagger.DetailsActivityComponent;
import pl.touk.qrgen.dagger.DetailsActivityQrCodeGenerationModule;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;

public class DetailsPageActivity extends AppCompatActivity {

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
        explodeIfPossible();
        setContentView(R.layout.activity_details);
        assembleDaggerComponent();
        setUpUi();
        extractQrGenerationTypeFromIntent();
    }

    private void explodeIfPossible() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
