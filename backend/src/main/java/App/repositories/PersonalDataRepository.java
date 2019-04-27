package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.PersonalData;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {

}