package pl.touk.qrgen.generation;

public enum Generation {
    PLAIN_TEXT;

    public static final String QR_GENERATION_TYPE = "qrGenerationType";
    public static final int QR_GENERATION_DEFAULT_ORDINAL = 0;

    public QrCodeGeneration get() {
        switch(ordinal()) {
            case 0 :
                return new PlainTextGeneration();
            default :
                return new PlainTextGeneration();
        }
    }
}
