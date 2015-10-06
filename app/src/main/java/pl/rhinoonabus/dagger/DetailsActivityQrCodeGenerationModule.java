package pl.rhinoonabus.dagger;

import dagger.Module;
import dagger.Provides;
import pl.rhinoonabus.ui.details.QrGenerationDetailsFormFactory;

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
