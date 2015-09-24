package pl.touk.qrgen.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    @Inject QrGenerationDetailsFormProvider qrGenerationDetailsFormProvider;
    private DetailsActivityComponent component;

    public static Intent getIntent(Context context) {
        return new Intent(context, DetailsPageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        assembleDaggerComponent();
        setUpUi();
        extractQrGenerationTypeFromIntent();
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

    private QrGenerationDetailsFormProvider extractQrGenerationTypeFromIntent() {
        int ordinal = getIntent().getIntExtra(
                QrGenerationDetailsFormProviderFactory.QR_GENERATION_PROVIDER_TYPE,
                QrGenerationDetailsFormProviderFactory.DEFAULT);
        return QrGenerationDetailsFormProviderFactory.values()[ordinal].get();
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
                        qrGenerationDetailsFormProvider.getGenerationFormFragment()
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
}
