package pl.rhinoonabus.ui.landing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.rhinoonabus.QrGenApplication;
import pl.rhinoonabus.dagger.DaggerLandingActivityComponent;
import pl.rhinoonabus.dagger.HasComponent;
import pl.rhinoonabus.dagger.LandingActivityComponent;
import pl.rhinoonabus.dagger.LandingActivityPagerAdapterModule;

public class HollowLandingActivityWithComponent extends AppCompatActivity
        implements HasComponent<LandingActivityComponent> {

    private LandingActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assembleDaggerComponent();
    }

    private void assembleDaggerComponent() {
        component = DaggerLandingActivityComponent.builder()
                .qrGenComponent(QrGenApplication.component(this))
                .landingActivityPagerAdapterModule(new LandingActivityPagerAdapterModule(this))
                .build();
    }

    public LandingActivityComponent getComponent() {
        return component;
    }
}
