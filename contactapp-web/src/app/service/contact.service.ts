import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ContactDTO} from '../model/contact.model';
import { Observable } from 'rxjs';

//const httpOptions = {
//  headers: new HttpHeaders({'Content-Type': 'application/json'})
//};

@Injectable({providedIn: 'root'})
export class ContactService {
  private API = 'http://localhost:8080/contacts';

  constructor(private http:HttpClient) {
  }

  public getContacts(): Observable<Array<ContactDTO>> {
    return this.http.get<ContactDTO[]>(this.API);
  }

  public getContactsByName(contact: ContactDTO): Observable<Array<ContactDTO>> {
    return this.http.get<ContactDTO[]>(this.API + "/" + contact.name);
  }

  public getContactById(contact: ContactDTO) {
    return this.http.get<ContactDTO>(this.API + "/" + contact.id);
  }

  public deleteContact(contact: ContactDTO) {
    return this.http.delete(this.API + "/" + contact.id);
  }

  public saveContact(contact: ContactDTO): Observable<any> {
    if (contact.id) {
      return this.http.post(this.API + "/" + contact.id, contact);
    } else {
      return this.http.post(this.API, contact);
    }
  }
}
