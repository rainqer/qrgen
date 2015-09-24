package pl.touk.qrgen.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.QrGenApplication;
import pl.touk.qrgen.R;
import pl.touk.qrgen.dagger.DaggerDetailsActivityComponent;
import pl.touk.qrgen.dagger.DetailsActivityComponent;
import pl.touk.qrgen.dagger.DetailsActivityQrCodeGenerationModule;
import pl.touk.qrgen.events.GenerateCodeButtonClickedEvent;
import pl.touk.qrgen.generation.Generation;
import pl.touk.qrgen.generation.QrCodeGeneration;
import pl.touk.qrgen.ui.view.FloatingActionButtonOverlay;

public class DetailsPageActivity extends AppCompatActivity {

    public static final String TAG = "LandingPageActivity";
    @Bind(R.id.tool_bar) Toolbar toolbar;
    @Inject FloatingActionButtonOverlay floatingActionButtonOverlay;
    @Inject Bus bus;
    @Inject QrCodeGeneration qrCodeGeneration;
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

    private QrCodeGeneration extractQrGenerationTypeFromIntent() {
        int ordinal = getIntent().getIntExtra(
                Generation.QR_GENERATION_TYPE,
                Generation.QR_GENERATION_DEFAULT_ORDINAL);
        return Generation.values()[ordinal].get();
    }

    private void setUpUi() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        floatingActionButtonOverlay.attach(this);
    }

    public DetailsActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onResume() {
        super.onResume();
        floatingActionButtonOverlay.showButtonWithAnimation(this);
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        floatingActionButtonOverlay.hideButtonWithAnimation(this);
        bus.unregister(this);
    }

    @Subscribe
    public void generateCodeButtonClicked(GenerateCodeButtonClickedEvent event) {
        startActivity(qrCodeGeneration.getIntentToActivityWithTranslation(this));
    }

}
