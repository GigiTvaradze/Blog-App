package com.MyFirstJavaProject.blogging_app.Controller;

import com.MyFirstJavaProject.blogging_app.Entity.View;
import com.MyFirstJavaProject.blogging_app.Service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/views")
public class ViewController {

    @Autowired
    private ViewService viewService;

    @GetMapping
    public List<View> getAllViews() {
        return viewService.getAllViews();
    }

    @GetMapping("/{id}")
    public Optional<View> getViewById(@PathVariable Long id) {
        return viewService.getViewById(id);
    }

    @PostMapping
    public View createView(@RequestBody View view) {
        return viewService.createView(view);
    }

    @PutMapping("/{id}")
    public View updateView(@PathVariable Long id, @RequestBody View updatedView) {
        return viewService.updateView(id, updatedView);
    }

    @DeleteMapping("/{id}")
    public void deleteView(@PathVariable Long id) {
        viewService.deleteView(id);
    }
}
