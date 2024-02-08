package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.Tag;
import com.MyFirstJavaProject.blogging_app.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTag(Long id, Tag updatedTag) {
        if (tagRepository.existsById(id)) {
            updatedTag.setTagId(id);
            return tagRepository.save(updatedTag);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
