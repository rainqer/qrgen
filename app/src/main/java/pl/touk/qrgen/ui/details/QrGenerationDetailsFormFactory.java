package pl.touk.qrgen.ui.details;

public enum QrGenerationDetailsFormFactory {
    PLAIN_TEXT,
    URL;

    public static final String QR_GENERATION_PROVIDER_TYPE = "qrGenerationProviderType";
    public static final int DEFAULT = 0;

    public QrGenerationDetailsForm get() {
        switch(ordinal()) {
            case 0 :
                return new PlainTextDetailsFormFragment();
            case 1 :
                return new UrlDetailsFormFragment();
            default :
                return new PlainTextDetailsFormFragment();
        }
    }
}
