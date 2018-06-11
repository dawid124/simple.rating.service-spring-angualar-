import {Component, OnInit} from '@angular/core';
import {Product} from '../../../models/product';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  product: Product;
  editMode: boolean;

  constructor(private route: ActivatedRoute,
              private productService: ProductService) {


    this.product = new Product();
    this.route.params.subscribe( params => {
      const productId: number = params.id;
      if (productId) {
        this.editMode = false;
        this.loadProductData(productId);
      } else {
        this.editMode = true;
      }
    });
  }

  ngOnInit() {

  }

  loadProductData(productId: number) {
    this.productService.getProductById(productId)
      .subscribe((product: Product) => {
        debugger;
        this.product = product;
    });
  }

  switchEditMode(value: boolean) {
    this.editMode = value;
  }
}
