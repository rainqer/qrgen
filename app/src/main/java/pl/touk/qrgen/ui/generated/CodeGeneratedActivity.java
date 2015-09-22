package pl.touk.qrgen.ui.generated;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

public class CodeGeneratedActivity extends FragmentActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, CodeGeneratedActivity.class);
    }
    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager().beginTransaction().add(new QrCreatorFragment(), "tag").commit();
    }
}
