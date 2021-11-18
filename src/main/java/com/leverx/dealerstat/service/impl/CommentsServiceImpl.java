package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.converter.CommentsConverter;
import com.leverx.dealerstat.dto.CommentDTO;
import com.leverx.dealerstat.model.Comment;
import com.leverx.dealerstat.repository.CommentsRepository;
import com.leverx.dealerstat.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository repository;

    private final CommentsConverter converter;

    @Autowired
    public CommentsServiceImpl(CommentsRepository repository, CommentsConverter converter) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public List<CommentDTO> getComments(Long userId) {
//        Optional<List<Comment>> comments = repository.findAll();
        return null;
    }

    @Override
    public CommentDTO getComment(Long userId, Long commentId) {
        Optional<Comment> comment = repository.findByAuthorIdAndId(userId, commentId);
        if (comment.isEmpty()) {
            System.out.println("ploho");
        }
        return converter.convertToDTO(comment.get());
        // return CommentDTO.toCommentDTO(comment);
      //  return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public void addComment(Comment comment) {
        repository.save(comment);
    }
}
