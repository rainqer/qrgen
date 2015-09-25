package pl.touk.qrgen.dagger;

import dagger.Component;
import pl.touk.qrgen.ui.details.DetailsPageActivity;
import pl.touk.qrgen.ui.details.QrGenerationDetailsForm;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormFactory;

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
        @DetailsActivityScope
        QrGenerationDetailsFormFactory providesDetailsActivityScope();
        void inject(DetailsPageActivity detailsPageActivity);
        void inject(QrGenerationDetailsForm fragment);
}
