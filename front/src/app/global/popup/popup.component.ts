import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {

  private _shown: boolean;

  constructor() {
  }

  ngOnInit() {
    this.shown = false;
  }

  show() {
    this.shown = true;
  }

  hide() {
    this.shown = false;
  }

  get shown(): boolean {
    return this._shown;
  }

  set shown(value: boolean) {
    this._shown = value;
  }
}
