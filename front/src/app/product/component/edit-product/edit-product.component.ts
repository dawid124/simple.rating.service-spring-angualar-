import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../models/product';
import {Type} from '../../../models/type';
import {FormControl, Validators} from '@angular/forms';
import {NewTypePopupComponent} from './new-type-popup/new-type-popup.component';
import {MatDialog} from '@angular/material';
import {ProductService} from '../../service/product.service';
import {UiPicture} from '../../../models/ui-picture';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {

  @Input('product') product: Product;
  pictures: Array<UiPicture>;
  types: Array<Type>;

  @Output() switchEditMode: EventEmitter<boolean> = new EventEmitter();

  typeControl = new FormControl('', [Validators.required]);
  priceControl = new FormControl('', [Validators.required]);
  nameControl = new FormControl('', [Validators.required]);

  constructor(public dialog: MatDialog,
              private productService: ProductService) { }

  ngOnInit() {
    this.pictures = [];
    this.addNewPicture();
    this.getAllType();
  }

  addNewPicture() {
    this.pictures.push(new UiPicture());
  }

  onSelectFile(event, index) {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();

      const file: File  = event.target.files[0];
      reader.readAsDataURL(file);

      reader.onload = () => {
        const picture: UiPicture = this.pictures[index];
        picture.data = reader.result;
        picture.name = file.name;
        console.log(picture.name);
      };

      this.addNewPicture();
    }
  }

  createProduct() {
    const formData: FormData = new FormData();
    formData.append('product', JSON.stringify(this.product));




    debugger;
    const pictures = this.pictures.splice(0, this.pictures.length - 1);
    for (const picture of pictures) {
      formData.append('picture',  new Blob([picture.data], {type: 'application/image'}));
    }

    this.productService.createProduct(formData)
      .subscribe((product: Product) => {
        this.product = product;
        this.switchEditMode.emit(false);
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
}
