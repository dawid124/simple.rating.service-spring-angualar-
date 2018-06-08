import {AfterViewInit, Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {animate, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.scss'],
  animations: [trigger('fadeInOut', [
    transition(':enter', [   // :enter is alias to 'void => *'
      style({opacity: 0}),
      animate(500, style({opacity: 1}))
    ]),
    transition(':leave', [   // :leave is alias to '* => void'
      animate(500, style({opacity: 0}))
    ])
  ])]
})
export class SignComponent implements OnInit {

  selectedTab: number;

  @Output() hideSignPopup: EventEmitter<any> = new EventEmitter();

  constructor() {
    this.selectedTab = 0;
  }

  ngOnInit() {

  }

  hidePopup() {
    this.hideSignPopup.emit();
  }

  switchTab(tabIndex: number) {
    this.selectedTab = tabIndex;
  }
}
