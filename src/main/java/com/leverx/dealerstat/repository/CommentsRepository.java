package com.leverx.dealerstat.repository;

import com.leverx.dealerstat.model.Comment;
import com.leverx.dealerstat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

   Optional<Comment> findById(Long id);

   Optional<List<Comment>> findAllByAuthorId(Long id);

   void deleteById(Long id);

}
