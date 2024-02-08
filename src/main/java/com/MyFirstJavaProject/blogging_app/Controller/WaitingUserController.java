package com.MyFirstJavaProject.blogging_app.Controller;

import com.MyFirstJavaProject.blogging_app.Entity.WaitingUser;
import com.MyFirstJavaProject.blogging_app.Service.WaitingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/waitingUsers")
public class WaitingUserController {

    @Autowired
    private WaitingUserService waitingUserService;

    @GetMapping
    public List<WaitingUser> getAllWaitingUsers() {
        return waitingUserService.getAllWaitingUsers();
    }

    @GetMapping("/{id}")
    public Optional<WaitingUser> getWaitingUserById(@PathVariable Long id) {
        return waitingUserService.getWaitingUserById(id);
    }

    @PostMapping
    public WaitingUser createWaitingUser(@RequestBody WaitingUser waitingUser) {
        return waitingUserService.createWaitingUser(waitingUser);
    }

    @PutMapping("/{id}")
    public WaitingUser updateWaitingUser(@PathVariable Long id, @RequestBody WaitingUser updatedWaitingUser) {
        return waitingUserService.updateWaitingUser(id, updatedWaitingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWaitingUser(@PathVariable Long id) {
        try {
            waitingUserService.deleteWaitingUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return handleException(e);
        }
    }

    // Exception handling method
    private ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
