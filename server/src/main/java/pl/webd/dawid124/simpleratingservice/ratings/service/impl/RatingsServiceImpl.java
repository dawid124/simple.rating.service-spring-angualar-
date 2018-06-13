package pl.webd.dawid124.simpleratingservice.ratings.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.interceptors.SendNotifications;
import pl.webd.dawid124.simpleratingservice.ratings.mapper.RatingsMapper;
import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;
import pl.webd.dawid124.simpleratingservice.ratings.service.RatingsService;

import java.util.List;

@Service
@Transactional
public class RatingsServiceImpl implements RatingsService {

    private RatingsMapper ratingsMapper;

    public RatingsServiceImpl(RatingsMapper ratingsMapper) {
        this.ratingsMapper = ratingsMapper;
    }

    @Override
    @SendNotifications
    public Rating createRating(Rating rating) {
        ratingsMapper.insertRating(rating);

        return rating;
    }

    @Override
    public List<Rating> getRatingsByProductId(long productId) {
        return ratingsMapper.getRatingsByProductId(productId);
    }

    @Override
    public List<String> getRatingUsers(long productId) {
        return ratingsMapper.getRatingUsers(productId);
    }
}
