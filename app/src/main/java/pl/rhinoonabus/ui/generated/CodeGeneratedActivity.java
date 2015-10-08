package pl.rhinoonabus.ui.generated;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.common.DecodedQrUsher;

import static pl.rhinoonabus.ui.generated.QrFragmentFactory.DEFAULT;
import static pl.rhinoonabus.ui.generated.QrFragmentFactory.QR_GENERATION_PROVIDER_TYPE;

public class CodeGeneratedActivity extends AppCompatActivity {

    @Bind(R.id.tool_bar) Toolbar toolbar;

    public static Intent getIntent(Context context) {
        return new Intent(context, CodeGeneratedActivity.class);
    }

    public static Intent getIntentForSpecificContent(Context context, String content) {
        return getIntent(context)
                .putExtra(Intent.EXTRA_TEXT, content);
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
        QrFragmentFactory fragmentFactory
                = QrFragmentFactory.values()[getQrFragmentTypeOrdinal()];
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_area, fragmentFactory.get())
                .commit();
    }

    private int getQrFragmentTypeOrdinal() {
        return getIntent().hasExtra(QR_GENERATION_PROVIDER_TYPE)
                ? getIntent().getIntExtra(QR_GENERATION_PROVIDER_TYPE, DEFAULT)
                : new DecodedQrUsher().getTypeOrdinal(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
