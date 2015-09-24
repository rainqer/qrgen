package pl.touk.qrgen.ui.details;

public enum QrGenerationDetailsFormProviderFactory {
    PLAIN_TEXT;

    public static final String QR_GENERATION_PROVIDER_TYPE = "qrGenerationProviderType";
    public static final int DEFAULT = 0;

    public QrGenerationDetailsFormProvider get() {
        switch(ordinal()) {
            case 0 :
                return new PlainTextGenerationDetailsFormProvider();
            default :
                return new PlainTextGenerationDetailsFormProvider();
        }
    }
}