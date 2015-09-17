package pl.touk.qrgen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.service.QrCodeImageGenerator;

public class QrCreatorFragment extends Fragment {

    @Bind(R.id.qr_image_view) ImageView qrCodeImageView;
    private QrCodeImageGenerator qrCodeImageGenerator = new QrCodeImageGenerator();

    public static QrCreatorFragment newInstance() {
        QrCreatorFragment fragment = new QrCreatorFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_qr_creator, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        createQrCode();
    }

    private void createQrCode() {
        try {
            qrCodeImageView.setImageBitmap(qrCodeImageGenerator.generate(getActivity(), "aaa"));
        } catch (WriterException e) {
            Toast.makeText(getActivity(), "QR Failed", Toast.LENGTH_LONG).show();
        }
    }

}
