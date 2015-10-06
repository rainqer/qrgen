package pl.rhinoonabus.dagger;

import dagger.Component;
import pl.rhinoonabus.ui.details.DetailsPageActivity;
import pl.rhinoonabus.ui.details.QrGenerationDetailsForm;
import pl.rhinoonabus.ui.details.QrGenerationDetailsFormFactory;

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
