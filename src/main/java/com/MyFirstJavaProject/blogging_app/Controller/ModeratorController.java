package com.MyFirstJavaProject.blogging_app.Controller;

import com.MyFirstJavaProject.blogging_app.Entity.Moderator;
import com.MyFirstJavaProject.blogging_app.Service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moderators")
public class ModeratorController {

    @Autowired
    private ModeratorService moderatorService;

    @GetMapping
    public ResponseEntity<String> getAllModerators() {
        try {
            List<Moderator> moderators = moderatorService.getAllModerators();
            return ResponseEntity.ok(moderators.toString());
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return handleException(e);
        }
    }

    @GetMapping("/{userId}/{blogId}")
    public Optional<Moderator> getModeratorById(@PathVariable Long userId, @PathVariable Long blogId) {
        return moderatorService.getModeratorById(userId, blogId);
    }

    @PostMapping
    public Moderator createModerator(@RequestBody Moderator moderator) {
        return moderatorService.createModerator(moderator);
    }

    @DeleteMapping("/{userId}/{blogId}")
    public void deleteModerator(@PathVariable Long userId, @PathVariable Long blogId) {
        moderatorService.deleteModerator(userId, blogId);
    }


    private ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
