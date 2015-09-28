package pl.touk.qrgen.ui.generated;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.animation.Animation;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;

import static pl.touk.qrgen.ui.generated.QrFragmentFactory.DEFAULT;
import static pl.touk.qrgen.ui.generated.QrFragmentFactory.QR_GENERATION_PROVIDER_TYPE;

public class CodeGeneratedActivity extends AppCompatActivity {

    public static final String TRANSLATION_CONTENT_KEY = "translationContent";

    @Bind(R.id.tool_bar) Toolbar toolbar;

    public static Intent getIntent(Context context) {
        return new Intent(context, CodeGeneratedActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created);
        setUpUi();
        applyFragment();
    }

    private void setUpUi() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void applyFragment() {
        int fragmentProviderOrdinal
                = getIntent().getIntExtra(QR_GENERATION_PROVIDER_TYPE, DEFAULT);
        QrFragmentFactory fragmentFactory
                = QrFragmentFactory.values()[fragmentProviderOrdinal];
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_area, fragmentFactory.get())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
