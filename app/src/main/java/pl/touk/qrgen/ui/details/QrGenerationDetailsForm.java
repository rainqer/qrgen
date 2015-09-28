package pl.touk.qrgen.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.generated.CodeGeneratedActivity;

public abstract class QrGenerationDetailsForm extends Fragment {

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
        ((DetailsPageActivity)getActivity()).getComponent().inject(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
                for (View view : provideBlinkingViews()) {
                    view.startAnimation(fadeIn);
                    view.setVisibility(View.VISIBLE);
                }
            }
        }, 800);
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

    protected abstract View[] provideBlinkingViews();
}
