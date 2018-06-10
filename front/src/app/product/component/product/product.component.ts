import {Component, OnInit} from '@angular/core';
import {Product} from '../../../models/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  product: Product;
  editMode: boolean;

  constructor() {
    this.product = new Product();
    this.editMode = true;
  }

  ngOnInit() {

  }

  switchEditMode(value: boolean) {
    this.editMode = value;
  }
}
