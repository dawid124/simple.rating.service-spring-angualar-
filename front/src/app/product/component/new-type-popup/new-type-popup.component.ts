import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormControl, Validators} from '@angular/forms';
import {Type} from '../../../models/type';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-new-type-popup',
  templateUrl: './new-type-popup.component.html',
  styleUrls: ['./new-type-popup.component.scss']
})
export class NewTypePopupComponent implements OnInit {

  nameControl = new FormControl('', [Validators.required]);
  type: Type;

  constructor(private dialogRef: MatDialogRef<NewTypePopupComponent>,
              private productService: ProductService,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.type = new Type();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  addNewProductType() {
    this.productService.createType(this.type)
      .subscribe((type: Type) => {
        this.dialogRef.close(type);
      });
  }
}
