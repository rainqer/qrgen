package pl.touk.qrgen.ui.generation;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import javax.inject.Inject;

import pl.touk.qrgen.translations.QrCodePlainTextTranslation;
import pl.touk.qrgen.translations.QrCodeTranslation;
import pl.touk.qrgen.ui.ResourceProvider;

public class AvailableCodeTranslationsListAdapter extends BaseAdapter {

//    private final int selectedTranslationViewBackground;
//    private final int idleTranslationViewBackground;
    private int selectedTranslationViewIndex = 0;

    private final QrCodeTranslation[] availableCodeTranslations = {
            new QrCodePlainTextTranslation(),
            new QrCodePlainTextTranslation()
    };

    @Inject
    public AvailableCodeTranslationsListAdapter(ResourceProvider resourceProvider) {
//        selectedTranslationViewBackground = resourceProvider.getColor(android.R.color.background_dark);
//        idleTranslationViewBackground = resourceProvider.getColor(android.R.color.background_light);
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

//    private int getBackgroundColorResIdForPosition(int position) {
//        return position == selectedTranslationViewIndex
//                ? selectedTranslationViewBackground
//                : idleTranslationViewBackground;
//    }

    @NonNull
    public QrCodeTranslation getActiveCodeTranslation() {
        return availableCodeTranslations[selectedTranslationViewIndex];
    }
}