package pl.touk.qrgen.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import pl.touk.qrgen.R;
import rx.functions.Action1;

public class ShareAction implements Action1<Uri> {

    private final Context context;

    public ShareAction(Context context) {
        this.context = context;
    }

    @Override
    public void call(Uri uri) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(
                    Intent.createChooser(share, context.getString(R.string.share_selector_message)));
    }
}
