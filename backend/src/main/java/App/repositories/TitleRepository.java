package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

}