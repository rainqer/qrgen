package pl.touk.qrgen.ui.scaning;

import javax.inject.Inject;

import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.LandingPageFragmentWithTitle;

public class ScanningFragment extends LandingPageFragmentWithTitle {

    @Inject
    public ScanningFragment() {
    }

    @Override
    public int getTitleResId() {
        return R.string.title_section2;
    }
}
