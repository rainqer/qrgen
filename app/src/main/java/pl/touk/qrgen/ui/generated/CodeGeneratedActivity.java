package pl.touk.qrgen.ui.generated;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import pl.touk.qrgen.R;

import static pl.touk.qrgen.ui.generated.QrFragmentFactory.DEFAULT;
import static pl.touk.qrgen.ui.generated.QrFragmentFactory.QR_GENERATION_PROVIDER_TYPE;

public class CodeGeneratedActivity extends FragmentActivity {

    public static final String TRANSLATION_CONTENT_KEY = "translationContent";

    public static Intent getIntent(Context context) {
        return new Intent(context, CodeGeneratedActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created);
        applyFragment();
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
}
