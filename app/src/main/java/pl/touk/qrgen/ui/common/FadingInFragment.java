package pl.touk.qrgen.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.VersionUtil;

public abstract class FadingInFragment extends Fragment {

    private final UiFadeIn uiFadeIn = new UiFadeIn();
    private Handler animationHandler = new Handler();
    protected abstract View[] provideBlinkingViews();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
    }

    private void initUi() {
        if (VersionUtil.lolipopTransitionsAvailable()) {
            animationHandler.postDelayed(uiFadeIn, 700);
        } else {
            showAllUi();
        }
    }

    private void showAllUi() {
        for (View view : provideBlinkingViews()) {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        animationHandler.removeCallbacks(uiFadeIn);
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
