package guru.springframework.spring6restmvc.autodi.services.i18n;

import guru.springframework.spring6restmvc.autodi.services.GreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"IT", "default"})
@Service("i18nService")
public class ItalianGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Ciao Mondo - Italiano";
    }
}
