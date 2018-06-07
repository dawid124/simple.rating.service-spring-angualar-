import { Component, OnInit } from '@angular/core';
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

  constructor() { }

  ngOnInit() {
  }

}
