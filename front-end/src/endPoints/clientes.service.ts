import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  private urlBase: string = environment.apiUrl;
  constructor(private http: HttpClient) {}

  public personFindAll(): Observable<any>{
    return this.http.get(this.urlBase+"/person/find-all");
  }

  public personSave(data:any): Observable<any>{
    return this.http.post(this.urlBase+"/person/create",data);
  }

  public personUpdate(data:any): Observable<any>{
    return this.http.put(this.urlBase+"/person/update",data);
  }
  
  public personDelete(id:number): Observable<any>{
    return this.http.get(this.urlBase+"/person/delete/"+id);
  }
}
