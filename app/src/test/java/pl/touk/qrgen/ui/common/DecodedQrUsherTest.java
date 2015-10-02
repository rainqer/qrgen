package pl.touk.qrgen.ui.common;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.touk.qrgen.ui.generated.QrFragmentFactory.PHONE;
import static pl.touk.qrgen.ui.generated.QrFragmentFactory.URL;

public class DecodedQrUsherTest {

    public static final String POLISH_MOBILE_NUMBER = "500100222";
    public static final String POLISH_MOBILE_NUMBER_PREFIX = "tel:500100222";
    public static final String POLISH_MOBILE_NUMBER_WITH_COUNTY_CODE = "+48500100222";
    public static final String POLISH_MOBILE_NUMBER_WITH_COUNTY_CODE_PREFIX = "tel:+48500100222";
    public static final String POLISH_HOME_NUMBER = "227830303";
    public static final String POLISH_HOME_NUMBER_PREFIX = "tel:227830303";
    public static final String POLISH_HOME_NUMBER_WITH_COUNTRY_CODE = "+48227830303";
    public static final String POLISH_HOME_NUMBER_WITH_COUNTRY_CODE_PREFIX = "tel:+48227830303";

    public static final String[] URLS = {
            "http://www.tlen.pl",
            "http://www.tlen.pl?flag=true",
            "http://www.tlen.pl/content",
            "http://www.tlen.pl/content.html",
            "http://www.tlen.pl/content.pdf",
            "www.tlen.pl",
            "www.tlen.pl?flag=true",
            "www.tlen.pl/content",
            "www.tlen.pl/content.html",
            "www.tlen.pl/content.pdf",
    };

    private DecodedQrUsher decodedQrUsher;

    @Before
    public void setUp() throws Exception {
        decodedQrUsher = new DecodedQrUsher();
    }

    @Test
    public void shouldReactOnPolishMobileNumber() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_MOBILE_NUMBER);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactOnPolishMobileNumberWithPrefix() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_MOBILE_NUMBER_PREFIX);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactOnPolishMobileNumberWithCountryCode() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_MOBILE_NUMBER_WITH_COUNTY_CODE);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactOnPolishMobileNumberWithCountryCodeAndPrefix() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_MOBILE_NUMBER_WITH_COUNTY_CODE_PREFIX);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactOnPolishHomeNumber() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_HOME_NUMBER);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactOnPolishHomeNumberWithPrefix() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_HOME_NUMBER_PREFIX);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactOnPolishHomeNumberWithCountryCode() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_HOME_NUMBER_WITH_COUNTRY_CODE);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactOnPolishHomeNumberWithCountryCodeAndPrefix() {
        // when
        int ordinal = decodedQrUsher.getTypeOrdinal(POLISH_HOME_NUMBER_WITH_COUNTRY_CODE_PREFIX);

        // then
        assertThat(ordinal).isEqualTo(PHONE.ordinal());
    }

    @Test
    public void shouldReactToUrls() {
        for (String url : URLS) {
            // when
            int ordinal = decodedQrUsher.getTypeOrdinal(url);

            // then
            assertThat(ordinal).isEqualTo(URL.ordinal());
        }
    }

}