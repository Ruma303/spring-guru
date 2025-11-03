package guru.springframework.spring6restmvc.manualdi.services;

public interface GreetingService {
    default String sayGreeting() {
        return "Hello from GreetingService";
    };
}
