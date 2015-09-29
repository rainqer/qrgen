package pl.touk.qrgen.ui.details;

import android.content.Context;
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
import pl.touk.qrgen.ui.VersionUtil;
import pl.touk.qrgen.ui.generated.CodeGeneratedActivity;

public abstract class QrGenerationDetailsForm extends Fragment {

    @Inject Bus bus;
    @Inject ResourceProvider resourceProvider;
    private final UiFadeIn uiFadeIn = new UiFadeIn();
    private Handler animationHandler = new Handler();

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
        initUi();
    }

    private void initUi() {
        if (VersionUtil.lolipopTransitionsAvailable()) {
            animationHandler.postDelayed(uiFadeIn, 700);
        } else {
            showAllUi();
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        animationHandler.removeCallbacks(uiFadeIn);
    }

    void showError() {
        Toast.makeText(
                getActivity(),
                resourceProvider.getString(R.string.translation_plain_text_error),
                Toast.LENGTH_SHORT
        ).show();
    }

    protected abstract View[] provideBlinkingViews();

    private void showAllUi() {
        for (View view : provideBlinkingViews()) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private class UiFadeIn implements Runnable {

        @Override
        public void run() {
            Context context = getActivity();
            if (context != null) {
                Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fadein);
                for (View view : provideBlinkingViews()) {
                    view.startAnimation(fadeIn);
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }
}
