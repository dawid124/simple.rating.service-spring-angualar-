package pl.webd.dawid124.simpleratingservice.ratings.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.ratings.mapper.RatingsMapper;
import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;
import pl.webd.dawid124.simpleratingservice.ratings.service.RatingsService;

@Service
@Transactional
public class RatingsServiceImpl implements RatingsService {

    private RatingsMapper ratingsMapper;

    public RatingsServiceImpl(RatingsMapper ratingsMapper) {
        this.ratingsMapper = ratingsMapper;
    }

    @Override
    public Rating createRating(Rating rating) {
        ratingsMapper.insertRating(rating);

        return rating;
    }
}
