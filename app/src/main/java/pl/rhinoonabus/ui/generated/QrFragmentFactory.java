package pl.rhinoonabus.ui.generated;

public enum QrFragmentFactory {
    PLAIN_TEXT,
    URL,
    PHONE,
    EMPTY;

    public static final String QR_GENERATION_PROVIDER_TYPE = "qrFragmentProviderType";
    public static final int DEFAULT = 0;

    public QrFragment get() {
        switch(ordinal()) {
            case 0 :
                return new PlainTextQrFragment();
            case 1 :
                return new UrlQrFragment();
            case 2 :
                return new PhoneQrFragment();
            case 3 :
                return new EmptyQrFragment();
            default :
                return new PlainTextQrFragment();
        }
    }
}
