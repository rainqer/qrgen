package pl.touk.qrgen.dagger;

import dagger.Module;
import dagger.Provides;
import pl.touk.qrgen.generation.QrCodeGeneration;

@Module
public final class DetailsActivityQrCodeGenerationModule {

    private final QrCodeGeneration generation;

    public DetailsActivityQrCodeGenerationModule(QrCodeGeneration generation) {
        this.generation = generation;
    }

    @DetailsActivityScope
    @Provides
    QrCodeGeneration providesQrCodeGeneration() {
        return generation;
    }
}
