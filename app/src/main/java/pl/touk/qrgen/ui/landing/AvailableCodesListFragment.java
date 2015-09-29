package pl.touk.qrgen.ui.landing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.zxing.client.android.decode.CaptureActivity;

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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        availableTranslationsList.setAdapter(null);
        availableTranslationsList.setOnItemClickListener(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), CaptureActivity.class));
//        availableCodeTranslationsListAdapter
//                .launchTranslationDetails((LandingPageActivity) getActivity(), view);
    }
}