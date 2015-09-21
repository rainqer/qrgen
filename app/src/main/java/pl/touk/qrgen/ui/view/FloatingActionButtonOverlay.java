package pl.touk.qrgen.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;

public class FloatingActionButtonOverlay {

    @Bind(R.id.floating_action_button) FloatingActionButton floatingActionButton;

    public void attach(Activity activity) {
        View view = createView(activity);
        getMainContentFromActivity(activity).addView(view);
        ButterKnife.bind(this, view);
    }

    private ViewGroup getMainContentFromActivity(Activity activity) {
        return (ViewGroup) activity.getWindow().getDecorView().getRootView().findViewById(android.R.id.content);
    }

    @NonNull
    public View createView(Activity activity) {
        return activity.getLayoutInflater().inflate(R.layout.floating_action_button, null, false);
    }

    public void showButtonWithAnimation(Context context) {
        floatingActionButton.setVisibility(View.VISIBLE);
        animateEntry(context);
    }

    public void hideButtonWithAnimation(Context context) {
        animateExit(context);
    }

    private void animateEntry(Context context) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fab_slide_in_bottom);
        floatingActionButton.startAnimation(anim);
    }

    private void animateExit(Context context) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fab_slide_out_bottom);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                floatingActionButton.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        floatingActionButton.startAnimation(anim);
    }
}
