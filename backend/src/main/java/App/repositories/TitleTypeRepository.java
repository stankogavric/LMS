package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.TitleType;

@Repository
public interface TitleTypeRepository extends JpaRepository<TitleType, Long> {

}