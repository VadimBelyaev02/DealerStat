package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.Comment;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.repository.CommentsRepository;
import com.leverx.dealerstat.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository repository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> getComments(Long userId) {
        return repository.findAllByAuthorId(userId);
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
        List<Comment> comments = repository.findAllByUserId(userId);
        //  Double rateSum = 0D;?
        return (comments.stream().map(Comment::getRate).reduce(0D, Double::sum))
                / (long) comments.size();
    }

    @Override
    public Map<User, Double> calculateAllRating() {
        List<Comment> comments = repository.findAll();
        Map<User, Integer> countOfRates = new HashMap<>();
        Map<User, Double> rating = new HashMap<>();
        for (Comment comment : comments) {
            User user = comment.getUser();
            if (rating.containsKey(user)) {
                rating.put(user, rating.get(user) + comment.getRate());
                countOfRates.put(user, countOfRates.get(user) + 1);
            } else {
                rating.put(user, comment.getRate());
                countOfRates.put(user, 1);
            }
        }
        for (Map.Entry<User, Double> entry : rating.entrySet()) {
            entry.setValue(entry.getValue() / countOfRates.get(entry.getKey()));
        }
        return rating;
       // Map<User, Double> newrate = rating.entrySet().stream()//.map(a -> a.setValue(rating.get(a) / countOfRates.get(a)))
       //         .collect(Collectors.toMap(k -> k.getKey(), v -> v.setValue(rating.get(Map.Entry::getKey)));
    }//usersConverter::convertToDTO, rating::get

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public void updateComment(Comment comment, Long id) {
        Comment commentFromDB = repository.findById(id).orElse(null);
        if (commentFromDB == null || !commentFromDB.getApproved()) {
            throw new NotFoundException("Comment is not found or is not approved");
        }
        commentFromDB.setMessage(comment.getMessage());
        commentFromDB.setRate(comment.getRate());
        repository.save(commentFromDB);
    }

    @Override
    public List<Comment> getUnapprovedComments() {
        return repository.findAllByApproved(false);
    }

    @Override
    public void approveComment(Long commentId) {
        Comment commentFromDB = repository.findById(commentId).orElseThrow(() -> {
            throw new NotFoundException("Comment is not found");
        });
        commentFromDB.setApproved(true);
        repository.save(commentFromDB);
    }
}
