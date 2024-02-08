package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.WaitingUser;
import com.MyFirstJavaProject.blogging_app.Repository.WaitingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaitingUserService {

    @Autowired
    private WaitingUserRepository waitingUserRepository;

    public List<WaitingUser> getAllWaitingUsers() {
        return waitingUserRepository.findAll();
    }

    public Optional<WaitingUser> getWaitingUserById(Long id) {
        return waitingUserRepository.findById(id);
    }

    public WaitingUser createWaitingUser(WaitingUser waitingUser) {
        return waitingUserRepository.save(waitingUser);
    }

    public WaitingUser updateWaitingUser(Long id, WaitingUser updatedWaitingUser) {
        if (waitingUserRepository.existsById(id)) {
            updatedWaitingUser.setWaitingUserId(id);
            return waitingUserRepository.save(updatedWaitingUser);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deleteWaitingUser(Long id) {
        waitingUserRepository.deleteById(id);
    }


}
