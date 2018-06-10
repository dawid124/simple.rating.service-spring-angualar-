import {Type} from './type';
import {Picture} from './picture';

export class Product {
  id: number;
  name: string;
  descriptions: string;
  price: number;
  type: Type;
  color: String;
  producer: string;


  constructor() {
    this.id = -1;
    this.name = '';
    this.descriptions = '';
    this.price = null;
    this.type = null;
    this.color = '';
    this.producer = '';
  }
}
