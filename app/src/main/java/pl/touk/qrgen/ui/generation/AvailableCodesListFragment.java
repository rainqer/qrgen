package pl.touk.qrgen.ui.generation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.events.GenerateCodeButtonClickedEvent;
import pl.touk.qrgen.ui.LandingActivityComponentProvider;

public class AvailableCodesListFragment extends Fragment {

    @Inject Bus bus;
    @Inject AvailableCodeTranslationsListAdapter availableCodeTranslationsListAdapter;
    @Bind(R.id.translations_list) ListView availableTranslationsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_available_translations, container, false);
        ButterKnife.bind(this, content);
        ((LandingActivityComponentProvider) getActivity()).getComponent().inject(this);
        availableTranslationsList.setAdapter(availableCodeTranslationsListAdapter);
        return content;
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Subscribe
    public void generateCodeButtonClicked(GenerateCodeButtonClickedEvent event) {
        Intent showGeneratedCodeIntent = availableCodeTranslationsListAdapter
                .getActiveCodeTranslation()
                .getIntentToActivityWithTranslation(getActivity());
        getActivity().startActivity(showGeneratedCodeIntent);
    }
}