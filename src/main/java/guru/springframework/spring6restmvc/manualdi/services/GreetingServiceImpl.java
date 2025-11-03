package guru.springframework.spring6restmvc.manualdi.services;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello from GreetingServiceImpl";
    }
}
