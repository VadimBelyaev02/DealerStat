package com.leverx.dealerstat.repository;

import com.leverx.dealerstat.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

   Optional<Comment> findByAuthorIdAndId(Long authorId, Long id);
}
