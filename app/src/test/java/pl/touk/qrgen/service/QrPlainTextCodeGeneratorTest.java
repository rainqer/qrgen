package pl.touk.qrgen.service;

import android.graphics.Bitmap;
import com.google.zxing.WriterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;

import rx.android.schedulers.AndroidSchedulers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(RobolectricGradleTestRunner.class)
public class QrPlainTextCodeGeneratorTest {

    private static final String TEST_CONTENT = "test content";

    @Test
    public void shouldCreateBitmapValidWhenInitializedNormally() throws WriterException {
        // given
        QrCodeGenerator generator = new QrPlainTextCodeGenerator();

        // when
        Bitmap bitmap = generator.generate(RuntimeEnvironment.application, TEST_CONTENT)
                .toBlocking()
                .first();

        // then
        assertThat(bitmap).isNotNull();
        assertThat(bitmap.getHeight()).isEqualTo(QrPlainTextCodeGenerator.DEFAULT_QR_SIZE);
        assertThat(bitmap.getWidth()).isEqualTo(QrPlainTextCodeGenerator.DEFAULT_QR_SIZE);
    }

    @Test
    public void shouldCreateBitmapValidWhenInitializedWithCustomSize() throws WriterException {
        // given
        int customSize = QrPlainTextCodeGenerator.DEFAULT_QR_SIZE * 2;
        QrCodeGenerator generator = new QrPlainTextCodeGenerator(customSize);

        // when
        Bitmap bitmap = generator.generate(RuntimeEnvironment.application, TEST_CONTENT)
                .toBlocking()
                .first();

        // then
        assertThat(bitmap).isNotNull();
        assertThat(bitmap.getHeight()).isEqualTo(customSize);
        assertThat(bitmap.getWidth()).isEqualTo(customSize);
    }
}