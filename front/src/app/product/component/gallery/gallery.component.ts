import {Component, Input, OnInit} from '@angular/core';
import {Picture} from '../../../models/picture';
import {api, environment} from '../../../../environments/environment';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit {

  @Input('pictures') pictures: Array<Picture>;
  preview: Picture;
  othersPicture: Array<Picture>;

  constructor() {

  }

  ngOnInit() {
    if (this.pictures && this.pictures.length > 0) {
      this.preview = this.pictures[0];
      this.othersPicture = this.pictures.filter(pic => pic !== this.preview);
    }
  }

  getImageUrl(src: string) {
    return `${environment.SERVER_ADDRESS}${api.PRODUCT.IMAGE_CONTROLLER}${src}`;
  }

  changePreview(picture: Picture) {
    this.preview = picture;
    this.othersPicture = this.pictures.filter(pic => pic !== this.preview);
  }
}
