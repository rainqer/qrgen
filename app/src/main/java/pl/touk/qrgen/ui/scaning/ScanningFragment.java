package pl.touk.qrgen.ui.scaning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.touk.qrgen.ui.qrselect.LandingActivityComponentProvider;

public class ScanningFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = super.onCreateView(inflater, container, savedInstanceState);
        ((LandingActivityComponentProvider) getActivity()).getComponent().inject(this);
        return content;
    }
}
