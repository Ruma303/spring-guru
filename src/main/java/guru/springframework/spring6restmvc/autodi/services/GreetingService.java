package guru.springframework.spring6restmvc.autodi.services;

public interface GreetingService {
    default String sayGreeting() {
        return "Hello from GreetingService";
    };
}
