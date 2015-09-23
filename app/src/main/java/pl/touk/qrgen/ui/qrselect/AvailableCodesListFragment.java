package pl.touk.qrgen.ui.qrselect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.squareup.otto.Bus;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;

public class AvailableCodesListFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Inject AvailableCodeTranslationsListAdapter availableCodeTranslationsListAdapter;
    @Bind(R.id.translations_list) ListView availableTranslationsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_available_translations, container, false);
        ButterKnife.bind(this, content);
        ((LandingActivityComponentProvider) getActivity()).getComponent().inject(this);
        availableTranslationsList.setAdapter(availableCodeTranslationsListAdapter);
        availableTranslationsList.setOnItemClickListener(this);
        return content;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        availableCodeTranslationsListAdapter
                .launchTranslationDetails((LandingPageActivity) getActivity(), position);
    }

}