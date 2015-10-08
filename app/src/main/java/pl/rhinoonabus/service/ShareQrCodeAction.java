package pl.rhinoonabus.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import pl.rhinoonabus.qrgen.R;
import rx.functions.Action1;

public class ShareQrCodeAction implements Action1<Uri> {

    private final Context context;

    public ShareQrCodeAction(Context context) {
        this.context = context;
    }

    @Override
    public void call(Uri uri) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(
                    Intent.createChooser(share, context.getString(R.string.share_qr_selector_message)));
    }
}
