package pl.rhinoonabus.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.squareup.otto.Bus;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.rhinoonabus.events.GenerateCodeButtonClickedEvent;
import pl.rhinoonabus.qrgen.R;

public class FloatingActionButtonOverlay {

    @Bind(R.id.floating_action_button) FloatingActionButton floatingActionButton;
    @Inject Bus bus;

    @Inject
    public FloatingActionButtonOverlay() {
    }

    public void attach(Activity activity, int container) {
        View view = createView(activity);
        getMainContentFromActivity(activity, container).addView(view);
        ButterKnife.bind(this, view);
    }

    private ViewGroup getMainContentFromActivity(Activity activity, int container) {
        return (ViewGroup) activity.getWindow().getDecorView().getRootView().findViewById(container);
    }

    @NonNull
    public View createView(Activity activity) {
        return activity.getLayoutInflater().inflate(R.layout.floating_action_button, null, false);
    }

    public void showButtonWithAnimation(Context context) {
        floatingActionButton.setVisibility(View.VISIBLE);
        animateEntry(context);
    }

    public void hideButton() {
        floatingActionButton.setVisibility(View.GONE);
    }

    private void animateEntry(Context context) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fab_slide_in_bottom);
        floatingActionButton.startAnimation(anim);
    }

    @OnClick(R.id.floating_action_button)
    public void floatingButtonClicked() {
        bus.post(new GenerateCodeButtonClickedEvent());
    }
}
