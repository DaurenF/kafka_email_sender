package com.example.producer.repository;

import com.example.producer.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(String sender);

    @Query("Select m from Message m order by m.id desc limit 10")
    List<Message> findLatestTenMessages();
}
