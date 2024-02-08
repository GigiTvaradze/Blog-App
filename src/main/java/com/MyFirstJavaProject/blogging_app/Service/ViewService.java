package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.View;
import com.MyFirstJavaProject.blogging_app.Repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViewService {

    @Autowired
    private ViewRepository viewRepository;

    public List<View> getAllViews() {
        return viewRepository.findAll();
    }

    public Optional<View> getViewById(Long id) {
        return viewRepository.findById(id);
    }

    public View createView(View view) {
        return viewRepository.save(view);
    }

    public View updateView(Long id, View updatedView) {
        if (viewRepository.existsById(id)) {
            updatedView.setViewId(id);
            return viewRepository.save(updatedView);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deleteView(Long id) {
        viewRepository.deleteById(id);
    }
}
