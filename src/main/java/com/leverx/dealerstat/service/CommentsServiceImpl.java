package com.leverx.dealerstat.service;

import com.leverx.dealerstat.model.Comment;
import com.leverx.dealerstat.dto.CommentDTO;
import com.leverx.dealerstat.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository repository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CommentDTO> getComments(Long userId) {
        return null;
    }

    @Override
    public CommentDTO getComment(Long userId, Long commentId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }
}
