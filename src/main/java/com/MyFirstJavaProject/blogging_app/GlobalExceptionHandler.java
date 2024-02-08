package com.MyFirstJavaProject.blogging_app;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "not_found"; // Create a custom error view
    }

    @ExceptionHandler(BindValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(BindValidationException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "bad_request"; // Create a custom error view
    }

}
