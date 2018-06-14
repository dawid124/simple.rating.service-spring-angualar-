import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../models/product';
import {Type} from '../../../models/type';
import {FormControl, Validators} from '@angular/forms';
import {NewTypePopupComponent} from '../include/new-type-popup/new-type-popup.component';
import {MatDialog} from '@angular/material';
import {ProductService} from '../../service/product.service';
import {Router} from '@angular/router';
import {Picture} from '../../../models/picture';
import {api, environment} from '../../../../environments/environment';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {

  @Input('product') product: Product;
  @Output() switchEditMode: EventEmitter<boolean> = new EventEmitter();

  types: Array<Type>;

  typeControl = new FormControl('', [Validators.required]);
  priceControl = new FormControl('', [Validators.required]);
  nameControl = new FormControl('', [Validators.required]);

  constructor(private dialog: MatDialog,
              private router: Router,
              private productService: ProductService) {
  }

  ngOnInit() {
    this.addNewPicture();
    this.getAllType();
  }

  addNewPicture() {
    this.product.pictures.push(new Picture());
  }

  onSelectFile(event, index) {
    if (!event.target.files && !event.target.files[0]) {
      return;
    }

    let picture: Picture = this.product.pictures[index];

    if (picture.id > 0) {
      picture.deleted = true;
      picture = new Picture();
      this.product.pictures.splice(index, 0, picture);
    } else {
      this.addNewPicture();
    }

    const reader = new FileReader();

    const file: File = event.target.files[0];
    reader.readAsDataURL(file);

    reader.onload = () => {
      picture.src = reader.result;
    };
  }

  createProduct() {
    const formData: FormData = new FormData();

    const pictures = this.product.pictures.filter(p => p.src && this.isUiPicture(p.src));
    for (const picture of pictures.filter(p => !p.id || p.id <= 0)) {
      formData.append('picture', new Blob([picture.src], {type: 'application/image'}));
    }

    this.product.pictures = this.product.pictures.filter(p => p.id);
    formData.append('product', JSON.stringify(this.product));

    this.productService.createProduct(formData)
      .subscribe((product: Product) => {
        this.router.navigate(['/product-details/', product.id]);
      });
  }

  showAddNewType(): void {
    const dialogRef = this.dialog.open(NewTypePopupComponent);

    dialogRef.afterClosed().subscribe((newType: Type) => {
      if (!newType) {
        return;
      }
      this.types.push(newType);
      this.product.type = newType;
    });
  }

  getAllType() {
    return this.productService.getAllTypes()
      .subscribe((types: Array<Type>) => {
        this.types = types;
      });
  }

  getImageUrl(src: string) {
    if (!src) {
      return '';
    }

    if (this.isUiPicture(src)) {
      return src;
    }
    return `${environment.SERVER_ADDRESS}${api.PRODUCT.IMAGE_CONTROLLER}${src}`;
  }

  isUiPicture(src: string) {
    return src.includes('data:image/jpeg;base64');
  }

  isFormValid() {
    return this.nameControl.valid && this.priceControl.valid && this.typeControl.valid;
  }
}
