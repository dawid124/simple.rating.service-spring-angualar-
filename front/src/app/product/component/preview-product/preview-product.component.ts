import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../models/product';
import {Router} from '@angular/router';

@Component({
  selector: 'app-preview-product',
  templateUrl: './preview-product.component.html',
  styleUrls: ['./preview-product.component.scss']
})
export class PreviewProductComponent implements OnInit {

  @Input('product') product: Product;
  @Output() switchEditMode: EventEmitter<boolean> = new EventEmitter();

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  calculateRating(): number {
    if (this.product.ratings.length === 0) {
      return 0;
    }
    const ratings: Array<number> = this.product.ratings.map((rating) => rating.rating);
    const sum = ratings.reduce((a, b) => a + b, 0);

    return Math.round((sum / ratings.length) * 100) / 100;
  }

  editProduct() {
    this.switchEditMode.emit(true);
    this.router.navigate(['/product-details/', this.product.id, 'edit']);
  }
}
