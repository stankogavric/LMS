package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.Title;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {

}