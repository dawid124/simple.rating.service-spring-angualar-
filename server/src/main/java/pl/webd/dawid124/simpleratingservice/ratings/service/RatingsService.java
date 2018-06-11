package pl.webd.dawid124.simpleratingservice.ratings.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;

public interface RatingsService {

    Rating createRating(Rating rating);
}
