import {Component, Input, OnInit} from '@angular/core';
import {RatingService} from '../../../service/rating.service';
import {Rating} from '../../../../models/rating';
import {AuthService} from '../../../../auth/auth.service';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit {

  ratingsNumber: Array<number> = [5, 4, 3, 2, 1];
  @Input('productId') productId: number;

  newRating: Rating;
  ratings: Array<Rating>;

  constructor(private ratingService: RatingService,
              private authService: AuthService) { }

  ngOnInit() {
    this.newRating = new Rating();
    this.ratings = [];
  }

  addRating() {
    this.newRating.date = new Date();
    this.newRating.userId = this.authService.userData.id;

    this.ratingService.createRating(this.newRating, this.productId)
      .subscribe((rating: Rating) => {
        this.ratings.unshift(rating);
      });
  }

  setRating(rating: number) {
    this.newRating.rating = rating;
  }
}
