import {Type} from './type';
import {Picture} from './picture';
import {Rating} from './rating';
import {Comment} from './comment';

export class Product {
  id: number;
  name: string;
  descriptions: string;
  price: number;
  type: Type;
  color: String;
  producer: string;
  pictures: Array<Picture>;
  ratings: Array<Rating>;
  comments: Array<Comment>;


  constructor() {
    this.id = -1;
    this.name = '';
    this.descriptions = '';
    this.pictures = [];
    this.ratings = [];
    this.comments = [];
    this.price = null;
    this.type = null;
    this.color = '';
    this.producer = '';
  }
}
