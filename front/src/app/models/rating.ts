export class Rating {
  rating: number;
  date: Date;
  descriptions: string;
  username: string;
  userId: number;
  productId: number;

  constructor() {
    this.rating = 0;
    this.userId = -1;
    this.productId = -1;
    this.date = null;
    this.descriptions = '';
    this.username = '';
  }
}
