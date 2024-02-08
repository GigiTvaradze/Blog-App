package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.Comment;
import com.MyFirstJavaProject.blogging_app.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        if (commentRepository.existsById(id)) {
            updatedComment.setCommentId(id);
            return commentRepository.save(updatedComment);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
