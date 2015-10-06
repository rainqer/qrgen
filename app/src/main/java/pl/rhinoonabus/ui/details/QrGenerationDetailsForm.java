package pl.rhinoonabus.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.squareup.otto.Bus;
import javax.inject.Inject;
import pl.rhinoonabus.dagger.Components;
import pl.rhinoonabus.dagger.DetailsActivityComponent;
import pl.rhinoonabus.qrgen.R;
import pl.rhinoonabus.ui.ResourceProvider;
import pl.rhinoonabus.ui.common.FadingInFragment;
import pl.rhinoonabus.ui.generated.CodeGeneratedActivity;

public abstract class QrGenerationDetailsForm extends FadingInFragment {

    @Inject Bus bus;
    @Inject ResourceProvider resourceProvider;

    public abstract void setupLaunchIntent(Intent intent);

    void launchActivityWithQrCode() {
        Intent intent = CodeGeneratedActivity.getIntent(getActivity());
        setupLaunchIntent(intent);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Components.<DetailsActivityComponent>from(getActivity()).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    void showError() {
        Toast.makeText(
                getActivity(),
                resourceProvider.getString(R.string.translation_plain_text_error),
                Toast.LENGTH_SHORT
        ).show();
    }
}
