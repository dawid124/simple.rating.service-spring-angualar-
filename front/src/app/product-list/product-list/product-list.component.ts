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
  allLoaded: boolean;

  constructor(private productService: ProductService,
              private router: Router) {
  }

  ngOnInit() {
    this.products = [];
    this.fetchProducts();
  }

  getImageUrl(src: string) {
    return `${environment.SERVER_ADDRESS}${api.PRODUCT.IMAGE_CONTROLLER}${src}`;
  }

  fetchProducts() {
    const fetchData: FetchData = {
      limit: environment.INFINITY_SCROLL_LIMIT,
      offset: this.products.length
    };

    this.productService.fetchProducts(fetchData)
      .subscribe((products: ProductListModel[]) => {
          // products = products.concat(products);
          // products = products.concat(products);
          // products = products.concat(products);
          // products.splice(0, 2);

          this.products = this.products.concat(products);

          if (products.length < environment.INFINITY_SCROLL_LIMIT) {
            this.allLoaded = true;
          }
        }
      );
  }

  goToDetails(product: ProductListModel) {
    this.router.navigate([`/product/${product.id}`]);
  }

  onScroll() {
    this.fetchProducts();
  }
}
