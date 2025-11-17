package guru.springboot.lombok.service;

import guru.springboot.lombok.model.Beer;
import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
}
