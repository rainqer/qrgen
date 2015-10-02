package pl.touk.qrgen.tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;

public class ClipBoardExporter {

    public static final String LABEL = "qr_code";
    private final String dataToExport;

    public ClipBoardExporter(String data) {
        dataToExport = data;
    }

    public void exportToClipBoard(@NonNull Context context) {
        ClipboardManager clipboard
                = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(LABEL, dataToExport);
        clipboard.setPrimaryClip(clip);
    }
}
