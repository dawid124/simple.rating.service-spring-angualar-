import {Injectable} from '@angular/core';
import {api, environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {Rating} from '../../models/rating';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  constructor(private httpClient: HttpClient) { }


  createRating(rating: Rating, productId: number): Observable<Rating> {
    return this.httpClient.post<Rating>(`${environment.SERVER_ADDRESS}${api.RATING.CREATE_RATING}${productId}`, rating);
  }

  getAllRatings(): Observable<Array<Rating>> {
    return this.httpClient.get<Array<Rating>>(`${environment.SERVER_ADDRESS}${api.RATING.ALL_RATING}`);
  }
}
