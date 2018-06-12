package pl.webd.dawid124.simpleratingservice.ratings.service;

import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;

import java.util.List;

public interface RatingsService {

    int createRating(Rating rating);

    List<Rating> getRatingsByProductId(long productId);
}
