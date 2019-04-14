package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}