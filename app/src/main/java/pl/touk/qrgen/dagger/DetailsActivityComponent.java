package pl.touk.qrgen.dagger;

import dagger.Component;
import pl.touk.qrgen.ui.details.PlainTextDetailsFormFragment;
import pl.touk.qrgen.ui.details.QrGenerationDetailsForm;
import pl.touk.qrgen.ui.details.QrGenerationDetailsFormProvider;
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
        @DetailsActivityScope QrGenerationDetailsFormProvider providesDetailsActivityScope();
        void inject(DetailsPageActivity detailsPageActivity);
        void inject(QrGenerationDetailsForm fragment);
}
