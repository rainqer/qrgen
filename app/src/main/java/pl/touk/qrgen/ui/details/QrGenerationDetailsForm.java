package pl.touk.qrgen.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;
import pl.touk.qrgen.events.GenerateCodeButtonClickedEvent;

public abstract class QrGenerationDetailsForm extends Fragment {

    @Inject Bus bus;

    public abstract Intent getIntentToActivityWithGeneratedQrCode();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DetailsPageActivity)getActivity()).getComponent().inject(this);
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

    @Subscribe
    public void generateCodeButtonClicked(GenerateCodeButtonClickedEvent event) {
        startActivity(getIntentToActivityWithGeneratedQrCode());
    }
}
