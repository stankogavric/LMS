package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}