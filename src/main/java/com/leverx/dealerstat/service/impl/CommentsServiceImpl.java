package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.Comment;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.repository.CommentsRepository;
import com.leverx.dealerstat.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository repository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> getComments(Long userId) {
        return repository.findAllByAuthorId(userId).orElseThrow(() -> {
            throw new NotFoundException("User is not found");
        });
    }

    @Override
    public Comment getComment(Long commentId) {
        return repository.findById(commentId).orElseThrow(() -> {
            throw new NotFoundException("Comment is not found");
        });
    }

    @Override
    public void deleteComment(Long commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public void addComment(Comment comment) {

        repository.save(comment);
    }

    @Override
    public User getAuthor(Long commentId) {
        Comment comment = repository.findById(commentId).orElseThrow(() -> {
            throw new NotFoundException("Comment is not found");
        });
        return comment.getAuthor();
    }

    @Override
    public Double calculateRating(Long userId) {
        List<Comment> comments = repository.findAll();
        if (comments.isEmpty()) {
            throw new NotFoundException("User or user's rating can't be find");
        }
        return (comments.stream().mapToDouble(Comment::getRate).sum()) / (long) comments.size();
    }

    @Override
    public Map<User, Double> calculateAllRating() {
        List<Comment> comments = repository.findAll();
      //  List<User> users = comments.stream().
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }
}
