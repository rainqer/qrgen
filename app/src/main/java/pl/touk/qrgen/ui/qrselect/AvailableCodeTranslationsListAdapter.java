package pl.touk.qrgen.ui.qrselect;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import javax.inject.Inject;
import pl.touk.qrgen.translations.QrCodePlainTextTranslation;
import pl.touk.qrgen.translations.QrCodeTranslation;

public class AvailableCodeTranslationsListAdapter extends BaseAdapter {

    private final QrCodeTranslation[] availableCodeTranslations = {
            new QrCodePlainTextTranslation(),
            new QrCodePlainTextTranslation(),
            new QrCodePlainTextTranslation()
    };

    @Inject
    public AvailableCodeTranslationsListAdapter() {
    }

    @Override
    public int getCount() {
        return availableCodeTranslations.length;
    }

    @Override
    public Object getItem(int position) {
        return availableCodeTranslations[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        QrCodeTranslation translation = availableCodeTranslations[position];
        convertView =  translation.isBound()
                    ? translation.getBoundView()
                    : translation.getTranslationViewAndBind(LayoutInflater.from(parent.getContext()));
        return convertView;
    }

    public void launchTranslationDetails(AppCompatActivity activity, int position) {
        availableCodeTranslations[position].launchActivityWithDetails(activity);
    }
}
