package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.Ad;
import com.MyFirstJavaProject.blogging_app.Repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    public Optional<Ad> getAdById(Long id) {
        return adRepository.findById(id);
    }

    public Ad createAd(Ad ad) {
        return adRepository.save(ad);
    }

    public Ad updateAd(Long id, Ad updatedAd) {
        if (adRepository.existsById(id)) {
            updatedAd.setAdId(id);
            return adRepository.save(updatedAd);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }
}
