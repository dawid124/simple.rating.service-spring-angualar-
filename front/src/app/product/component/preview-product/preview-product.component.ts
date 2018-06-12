import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../../models/product';

@Component({
  selector: 'app-preview-product',
  templateUrl: './preview-product.component.html',
  styleUrls: ['./preview-product.component.scss']
})
export class PreviewProductComponent implements OnInit {

  @Input('product') product: Product;

  constructor() {
  }

  ngOnInit() {
  }

  calculateRating(): number {
    const ratings: Array<number> = this.product.ratings.map((rating) => rating.rating);
    const sum = ratings.reduce((a, b) => a + b, 0);

    return Math.round((sum / ratings.length) * 100) / 100;
  }
}
