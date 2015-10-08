package pl.rhinoonabus.ui.common;

import java.util.regex.Pattern;
import pl.rhinoonabus.ui.generated.QrFragmentFactory;

public class DecodedQrUsher {

    public int getTypeOrdinal(String content) {
        if (content == null || content.trim().isEmpty()) {
            return QrFragmentFactory.EMPTY.ordinal();
        }
        if (PatternHolder.get().urlLink.matcher(content).matches()
                || PatternHolder.get().urlLinkWithoutHttp.matcher(content).matches()) {
            return QrFragmentFactory.URL.ordinal();
        } else if (PatternHolder.get().phoneNumberPattern.matcher(content).matches()
                ||PatternHolder.get().phoneNumberPatternWithPrefix.matcher(content).matches()) {
            return QrFragmentFactory.PHONE.ordinal();
        }
        return QrFragmentFactory.PLAIN_TEXT.ordinal();
    }

    private static class PatternHolder {

        private static PatternHolder instance = null;

        public static PatternHolder get() {
            if (instance == null) {
                instance = new PatternHolder();
            }
            return instance;
        }

        private Pattern phoneNumberPattern = Pattern.compile("^(?:\\+\\d{1,3}|0\\d{1,3}|00\\d{1,2})?(?:\\s?\\(\\d+\\))?(?:[-\\/\\s.]|\\d)+$");
        private Pattern phoneNumberPatternWithPrefix = Pattern.compile("^tel:(?:\\+\\d{1,3}|0\\d{1,3}|00\\d{1,2})?(?:\\s?\\(\\d+\\))?(?:[-\\/\\s.]|\\d)+$");
        private Pattern urlLink = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        private Pattern urlLinkWithoutHttp = Pattern.compile("^www.[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }
}
