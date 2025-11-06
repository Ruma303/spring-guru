package guru.springframework.spring6restmvc.autodi.services;

import org.springframework.stereotype.Service;

@Service("settingGreetingBean")
public class GreetingServiceSetterInjection implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hey I'm Setting a Greeting!";
    }
}
