package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.Moderator;
import com.MyFirstJavaProject.blogging_app.Repository.ModeratorRepository;
import com.MyFirstJavaProject.blogging_app.Entity.ModeratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeratorService {

    @Autowired
    private ModeratorRepository moderatorRepository;

    public List<Moderator> getAllModerators() {
        return moderatorRepository.findAll();
    }

    public Optional<Moderator> getModeratorById(Long userId, Long blogId) {
        ModeratorId moderatorId = new ModeratorId(userId, blogId);
        return moderatorRepository.findById(moderatorId);
    }

    public Moderator createModerator(Moderator moderator) {
        return moderatorRepository.save(moderator);
    }

    public void deleteModerator(Long userId, Long blogId) {
        ModeratorId moderatorId = new ModeratorId(userId, blogId);
        moderatorRepository.deleteById(moderatorId);
    }
}
