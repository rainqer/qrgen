package pl.touk.qrgen.ui.qrselect;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import pl.touk.qrgen.R;
import pl.touk.qrgen.ui.ResourceProvider;
import pl.touk.qrgen.ui.qr.QrCodePlainTextType;
import pl.touk.qrgen.ui.qr.QrCodeType;
import pl.touk.qrgen.ui.qr.QrCodeUrlType;

public class AvailableCodeTranslationsListAdapter extends BaseAdapter {

    private final QrCodeType[] availableCodeTypes;

    @Inject
    public AvailableCodeTranslationsListAdapter(ResourceProvider resourceProvider) {
        availableCodeTypes = new QrCodeType[]{
                new QrCodePlainTextType(resourceProvider),
                new QrCodeUrlType(resourceProvider),
        };
    }

    @Override
    public int getCount() {
        return availableCodeTypes.length;
    }

    @Override
    public Object getItem(int position) {
        return availableCodeTypes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflateNewViewAndBind(LayoutInflater.from(parent.getContext()), position);
        }
        QrCodeType qrCodeType = availableCodeTypes[position];
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.subtitle.setText(qrCodeType.getSubTitleResId());
        viewHolder.title.setText(qrCodeType.getTitleResId());
        viewHolder.description.setText(qrCodeType.getDescriptionResId());
        viewHolder.image.setImageResource(qrCodeType.getDrawableResId());
        return convertView;
    }

    private View inflateNewViewAndBind(LayoutInflater inflater, int position) {
        QrCodeType qrCodeType = availableCodeTypes[position];
        View view = inflater.inflate(QrCodeType.QR_TYPE_CARD_RES_ID, null);
        view.setTag(new ViewHolder(view, qrCodeType));
        return view;
    }

    public void launchTranslationDetails(AppCompatActivity activity, View view) {
        ViewHolder viewHolder = ((ViewHolder) view.getTag());
        viewHolder.qrCodeType.launchActivityWithDetailsForm(activity, viewHolder.title);
    }

    private static class ViewHolder {
        TextView subtitle;
        TextView title;
        TextView description;
        ImageView image;
        QrCodeType qrCodeType;

        public ViewHolder(View view, QrCodeType qrCodeType) {
            this.qrCodeType = qrCodeType;
            subtitle = ButterKnife.findById(view, R.id.qr_code_type_top_title);
            title = ButterKnife.findById(view, R.id.qr_code_type_title);
            description = ButterKnife.findById(view, R.id.qr_code_type_content);
            image = ButterKnife.findById(view, R.id.qr_code_type_icon);
            title.setId(qrCodeType.getTransitionViewId());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                title.setTransitionName(qrCodeType.getDrawableViewTransitionName());
            }
        }
    }
}
