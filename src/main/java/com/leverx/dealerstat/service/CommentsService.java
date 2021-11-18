package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.CommentDTO;
import com.leverx.dealerstat.model.Comment;

import java.util.List;

public interface CommentsService {

    List<CommentDTO> getComments(Long userId);

    CommentDTO getComment(Long userId, Long commentId);

    void deleteComment(Long commentId);

    void addComment(Comment comment);
}
