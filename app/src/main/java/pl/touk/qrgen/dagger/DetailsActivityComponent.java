package pl.touk.qrgen.dagger;

import dagger.Component;
import pl.touk.qrgen.generation.QrCodeGeneration;
import pl.touk.qrgen.ui.details.DetailsPageActivity;

@DetailsActivityScope
@Component (
        dependencies = {
                QrGenComponent.class
        },
        modules = {
                DetailsActivityQrCodeGenerationModule.class
        }
)
public interface DetailsActivityComponent extends QrGenComponent {
        @DetailsActivityScope QrCodeGeneration providesDetailsActivityScope();
        void inject(DetailsPageActivity detailsPageActivity);
}
