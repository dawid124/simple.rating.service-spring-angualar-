import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../product/service/product.service';
import {FetchData} from '../../models/fetch-data';
import {ProductListModel} from '../../models/product-list-model';
import {api, environment} from '../../../environments/environment';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: Array<ProductListModel>;

  constructor(private productService: ProductService,
              private router: Router) {
  }

  ngOnInit() {
    this.fetchProducts();
  }

  getImageUrl(src: string) {
    return `${environment.SERVER_ADDRESS}${api.PRODUCT.IMAGE_CONTROLLER}${src}`;
  }

  fetchProducts() {
    const fetchData: FetchData = {
      limit: 10,
      offset: 0
    };

    this.productService.fetchProducts(fetchData)
      .subscribe((products: Array<ProductListModel>) => {
          this.products = products;
        }
      );
  }

  goToDetails(product: ProductListModel) {
    this.router.navigate([`/product/${product.id}`]);
  }
}
