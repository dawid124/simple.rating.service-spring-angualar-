import {Component, OnInit} from '@angular/core';
import {Product} from '../../../models/product';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../service/product.service';
import {ProductListModel} from '../../../models/product-list-model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  product: Product;
  editMode: boolean;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private productService: ProductService) {


    this.route.params.subscribe(params => {
      const productId: number = params.id;
      this.editMode = params.edit ? true : false;

      if (productId) {
        this.loadProductData(productId);
      } else {
        this.product = new Product();
      }
    });
  }

  ngOnInit() {

  }

  loadProductData(productId: number) {
    this.productService.getProductById(productId)
      .subscribe((product: Product) => {
        this.product = product;
      }, (err) => {
        console.log(err);
        if (err.status = 404) {
          this.notFoundRedirect();
        }
      });
  }

  notFoundRedirect() {
    this.router.navigate([`/404`]);
  }

  switchEditMode(value: boolean) {
    this.editMode = value;
  }
}
