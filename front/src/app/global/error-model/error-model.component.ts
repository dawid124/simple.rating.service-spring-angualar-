import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-error-model',
  templateUrl: './error-model.component.html',
  styleUrls: ['./error-model.component.scss']
})
export class ErrorModelComponent implements OnInit {

  message: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private translate: TranslateService) {
  }

  ngOnInit() {
    this.translate.get(this.data.errorCode).subscribe((res: string) => {
      console.log(res);
      this.message = res;
    });
  }

}
