import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../../models/product';
import {api, environment} from '../../../../environments/environment';

@Component({
  selector: 'app-preview-product',
  templateUrl: './preview-product.component.html',
  styleUrls: ['./preview-product.component.scss']
})
export class PreviewProductComponent implements OnInit {

  @Input('product') product: Product;

  constructor() { }

  ngOnInit() {
  }

  getImageUrl(src: string) {
    return `${environment.SERVER_ADDRESS}${api.PRODUCT.IMAGE_CONTROLLER}${src}`;
  }
}
