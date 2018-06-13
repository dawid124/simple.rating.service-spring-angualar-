package pl.webd.dawid124.simpleratingservice.ratings.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;
import pl.webd.dawid124.simpleratingservice.ratings.service.RatingsService;

@RestController
public class RatingController {

    private RatingsService ratingsService;

    public RatingController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @RequestMapping(value = "/rating", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> creteNewRating(@RequestBody Rating rating) throws AuthenticationException {

        if (!rating.valid()) {
            return new ResponseEntity<>("NOT_VALID", HttpStatus.BAD_REQUEST);
        }

        ratingsService.createRating(rating);

        if (rating.getId() > 0) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
