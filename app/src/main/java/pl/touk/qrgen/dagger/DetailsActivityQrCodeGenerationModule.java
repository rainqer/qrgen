package pl.touk.qrgen.dagger;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormFactory;

@Module
public final class DetailsActivityQrCodeGenerationModule {

    private final QrGenerationDetailsFormFactory generation;

    public DetailsActivityQrCodeGenerationModule(QrGenerationDetailsFormFactory generation) {
        this.generation = generation;
    }

    @DetailsActivityScope
    @Provides
    QrGenerationDetailsFormFactory providesQrCodeGeneration() {
        return generation;
    }
}
