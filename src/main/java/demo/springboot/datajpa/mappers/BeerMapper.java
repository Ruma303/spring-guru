package demo.springboot.datajpa.mappers;

import org.mapstruct.Mapper;

import demo.springboot.datajpa.dto.BeerDTO;
import demo.springboot.datajpa.model.Beer;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
