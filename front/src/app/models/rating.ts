export class Rating {
  rating: number;
  date: Date;
  comment: string;
  username: string;
  userId: number


  constructor() {
    this.rating = 0;
    this.userId = -1;
    this.date = null;
    this.comment = '';
    this.username = '';
  }
}
