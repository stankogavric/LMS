package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	Iterable<Message> findByRecipientOrSender(String recipient, String sender);
}
