package ru.tpu.andrew.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tpu.andrew.dtos.MessagesStatistics;
import ru.tpu.andrew.models.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

//@Query("SELECT NEW ru.tpu.andrew.dtos.MessagesStatistics(v.receiver. v.sender, COUNT(v))  FROM MessagesStatistics m" +
//        "    WHERE EXISTS" +
//        "        (SELECT spouseAuthor FROM Author spouseAuthor WHERE spouseAuthor = auth.spouse)")
////     List<Message> findAllByUserId(Long userId);
////


     @Query("select new ru.tpu.andrew.dtos.MessagesStatistics(v.sender.username, v.receiver.username, count(v.sender)) " +
             "from  Message v inner join User u on u.id = v.sender.id  group by v.sender.username, v.receiver.username" )
     List<MessagesStatistics> countMessagesFromUserToAnotherUser();

     @Query("select msg from Message msg  join User u ON msg.receiver.id = u.id where msg.receiver.id = :currentUserId")
     List<Message> findAllPersonalMessages(Long currentUserId);

}
