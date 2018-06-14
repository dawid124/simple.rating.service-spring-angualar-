import {Component, Input, OnInit} from '@angular/core';
import {RatingService} from '../../../service/rating.service';
import {Rating} from '../../../../models/rating';
import {AuthService} from '../../../../auth/auth.service';
import {Product} from '../../../../models/product';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit {

  @Input('product') product: Product;
  newRating: Rating;


  constructor(private ratingService: RatingService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.clearNewRatingData();
  }

  addRating() {
    this.newRating.date = new Date();
    this.newRating.userId = this.authService.userData.id;
    this.newRating.username = this.authService.userData.username;
    this.newRating.productId = this.product.id;

    this.ratingService.createRating(this.newRating)
      .subscribe((rating: Rating) => {
        this.product.ratings.push(rating);
        this.clearNewRatingData();
      });
  }

  clearNewRatingData() {
    this.newRating = new Rating();
  }

  setRating(rating: number) {
    this.newRating.rating = rating;
  }

  isNotRatingByUser(): boolean {
    if (!this.isLogged()) {
      return true;
    }

    const foundRating = this.product.ratings.filter((rating) => rating.userId === this.authService.userData.id);

    return foundRating.length === 0;
  }

  isLogged() {
    if (this.authService.userData) {
      return true;
    }
    return false;
  }

  getRatingsNumbers(): Array<number> {
    return [5, 4, 3, 2, 1];
  }
}
