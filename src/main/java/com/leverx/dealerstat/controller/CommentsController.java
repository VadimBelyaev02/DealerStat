package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.CommentDTO;
import com.leverx.dealerstat.model.Comment;
import com.leverx.dealerstat.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
public class CommentsController {

    private final CommentsService service;

    @Autowired
    public CommentsController(CommentsService service) {
        this.service = service;
    }

    @GetMapping("/users/{id}/comments")
    public ResponseEntity<List<CommentDTO>> listOfComments(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        List<CommentDTO> comments = service.getComments(id);
        //

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/users/{userId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("userId") Long userId,
                                                 @PathVariable("commentId") Long commentId) {
        if (userId == null || commentId == null) {
            return ResponseEntity.badRequest().build();
        }

        CommentDTO commentDTO = service.getComment(userId, commentId);
        return ResponseEntity.ok(commentDTO);
    }


    @PostMapping("/articles/comments")
    public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody Comment comment) {
        service.addComment(comment);
        //return ResponseEntity.ok(comment);
        return null;
    }

    @DeleteMapping("/users/comments")
    public ResponseEntity<CommentDTO> deleteComment(@Valid @RequestBody Comment comment) {
        return null;
    }

    @PutMapping("/articles/comments")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody Comment comment) {
        return null;
    }



}
