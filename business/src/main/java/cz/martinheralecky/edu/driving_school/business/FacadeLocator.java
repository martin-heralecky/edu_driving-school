package cz.martinheralecky.edu.driving_school.business;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class FacadeLocator {
    public static Facade facade;

    @Reference
    Facade facadeRef;

    @Activate
    void activate() {
        facade = facadeRef;
    }
}
