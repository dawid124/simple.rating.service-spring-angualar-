import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {api, environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {Product} from '../../models/product';
import {Type} from '../../models/type';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  createProduct(form: FormData): Observable<Product> {
    // const options = { content: form };
    return this.httpClient.post<Product>(`${environment.SERVER_ADDRESS}${api.PRODUCT.NEW_PRODUCT}`, form);
  }

  createType(type: Type): Observable<Type> {
    return this.httpClient.post<Type>(`${environment.SERVER_ADDRESS}${api.PRODUCT.NEW_TYPE}`, type);
  }

  getAllTypes(): Observable<Array<Type>> {
    return this.httpClient.get<Array<Type>>(`${environment.SERVER_ADDRESS}${api.PRODUCT.ALL_TYPES}`);
  }
}

