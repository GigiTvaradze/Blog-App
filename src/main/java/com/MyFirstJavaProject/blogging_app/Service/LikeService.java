package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.Like;
import com.MyFirstJavaProject.blogging_app.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public Optional<Like> getLikeById(Long id) {
        return likeRepository.findById(id);
    }

    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    public Like updateLike(Long id, Like updatedLike) {
        if (likeRepository.existsById(id)) {
            updatedLike.setLikeId(id);
            return likeRepository.save(updatedLike);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}
