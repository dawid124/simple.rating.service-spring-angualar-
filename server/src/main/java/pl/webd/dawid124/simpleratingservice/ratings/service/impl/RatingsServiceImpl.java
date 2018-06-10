package pl.webd.dawid124.simpleratingservice.ratings.service.impl;

import pl.webd.dawid124.simpleratingservice.ratings.mapper.RatingsMapper;

public class RatingsServiceImpl {

    private RatingsMapper ratingsMapper;

    public RatingsServiceImpl(RatingsMapper ratingsMapper) {
        this.ratingsMapper = ratingsMapper;
    }
}
