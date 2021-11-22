package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.converter.CommentsConverter;
import com.leverx.dealerstat.converter.UsersConverter;
import com.leverx.dealerstat.dto.CommentDTO;
import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.model.Comment;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.service.CommentsService;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CommentsController {

    private final UsersService usersService;
    private final CommentsService commentsService;
    private final CommentsConverter commentsConverter;
    private final UsersConverter usersConverter;

    @Autowired
    public CommentsController(UsersService usersService,
                              CommentsService commentsService,
                              CommentsConverter commentsConverter,
                              UsersConverter usersConverter) {
        this.usersService = usersService;
        this.commentsService = commentsService;
        this.commentsConverter = commentsConverter;
        this.usersConverter = usersConverter;
    }

    @GetMapping("/users/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getUserComments(@PathVariable("id") Long id) {
        List<CommentDTO> commentDTOS = commentsService.getComments(id).stream()
                .map(commentsConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(commentDTOS);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("commentId") Long commentId) {
        CommentDTO commentDTO = commentsConverter.convertToDTO(commentsService.getComment(commentId));
        return ResponseEntity.ok(commentDTO);
    }


    @PostMapping("/articles/comments")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User author = usersService.findByEmail(email);
        commentDTO.setAuthorId(author.getId());
        Comment comment = commentsConverter.convertToModel(commentDTO);
        commentsService.addComment(comment);
        return null;
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Long userId = usersService.findByEmail(email).getId();

        if (!commentsService.getAuthor(commentId).getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        commentsService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/articles/{id}/comments")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO commentDTO,
                                                    @PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/rating/{id}")
    public ResponseEntity<Double> getTraderRating(@PathVariable("id") Long id) {
        Double rate = commentsService.calculateRating(id);
        return ResponseEntity.ok(rate);
    }

    @GetMapping("/rating")
    public ResponseEntity<Map<UserDTO, Double>> getAllRatings() {
        Map<User, Double> rating = commentsService.calculateAllRating();
        Map<UserDTO, Double> result = rating.keySet().stream()
                .collect(Collectors.toMap(usersConverter::convertToDTO, rating::get));
        return ResponseEntity.ok(result);
    }

//DELETE /users /:id/comments/:id - удалить, удалить может только автор отзыва

}
