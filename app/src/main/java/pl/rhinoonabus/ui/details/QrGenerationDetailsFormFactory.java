package pl.rhinoonabus.ui.details;

public enum QrGenerationDetailsFormFactory {
    PLAIN_TEXT,
    URL,
    PHONE;

    public static final String QR_GENERATION_PROVIDER_TYPE = "qrGenerationProviderType";
    public static final int DEFAULT = 0;

    public QrGenerationDetailsForm get() {
        switch(ordinal()) {
            case 0 :
                return new PlainTextDetailsFormFragment();
            case 1 :
                return new UrlDetailsFormFragment();
            case 2 :
                return new PhoneDetailsFormFragment();
            default :
                return new PlainTextDetailsFormFragment();
        }
    }
}
