package pl.rhinoonabus.tools;

public class UrlCorrector {

    private static final String HTTP_PREFIX = "http://";
    private static final String HTTPS_PREFIX = "https://";

    public String getInCorrectFormat(String rawData) {
        String url = removeSpacesFromData(rawData);
        return hasHyperTextPrefix(url) ? url : HTTP_PREFIX + url;
    }

    private String removeSpacesFromData(String rawData) {
        return rawData.replace(" ", "");
    }

    private boolean hasHyperTextPrefix(String url) {
        return url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX);
    }
}
