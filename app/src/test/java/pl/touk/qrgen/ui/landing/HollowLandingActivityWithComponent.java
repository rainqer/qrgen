package pl.touk.qrgen.ui.landing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.touk.qrgen.QrGenApplication;
import pl.touk.qrgen.dagger.DaggerLandingActivityComponent;
import pl.touk.qrgen.dagger.HasComponent;
import pl.touk.qrgen.dagger.LandingActivityComponent;
import pl.touk.qrgen.dagger.LandingActivityPagerAdapterModule;

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
