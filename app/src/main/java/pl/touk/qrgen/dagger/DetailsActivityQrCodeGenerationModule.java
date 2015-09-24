package pl.touk.qrgen.dagger;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormProvider;

@Module
public final class DetailsActivityQrCodeGenerationModule {

    private final QrGenerationDetailsFormProvider generation;

    public DetailsActivityQrCodeGenerationModule(QrGenerationDetailsFormProvider generation) {
        this.generation = generation;
    }

    @DetailsActivityScope
    @Provides
    QrGenerationDetailsFormProvider providesQrCodeGeneration() {
        return generation;
    }
}
