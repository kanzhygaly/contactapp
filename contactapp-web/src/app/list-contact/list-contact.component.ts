import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ContactDTO} from '../model/contact.model';
import {ContactService} from '../service/contact.service';
import {DomSanitizer} from '@angular/platform-browser';
import {FormControl} from '@angular/forms';
import {filter, switchMap, debounceTime, catchError} from 'rxjs/operators';
import {EMPTY} from 'rxjs';

@Component({
  selector: 'app-list-contact',
  templateUrl: './list-contact.component.html',
  styleUrls: ['./list-contact.component.css']
})
export class ListContactComponent implements OnInit {

  // Contact search control field
  findControl = new FormControl();
  // Search error
  error:boolean = false;
  contacts:Array<ContactDTO>;

  constructor(private router:Router, private contactService:ContactService, private sanitizer:DomSanitizer) {
  }

  ngOnInit() {
    this.findControl.valueChanges.pipe(
      // Filters when the input is less than 2 chars
      filter(value => value.length > 2),
      // Wait 1 sec
      debounceTime(1000),
      // Request data
      switchMap(value => this.contactService.getContactsByName(value).pipe(
        // Error handling
        catchError(err => {
          this.contacts = null;
          this.error = true;
          return EMPTY;
        })
        )
      )
    ).subscribe((data:Array<ContactDTO>) => { // Receive data
      if (data && data.length) {
        this.contacts = data;
        this.error = false;
      } else {
        this.error = true;
      }
    });

    this.contactService.getContacts().subscribe((data:Array<ContactDTO>) => {
      this.contacts = data;
    });
  }

  editContact(contact:ContactDTO):void {
    localStorage.removeItem("editContactId");
    localStorage.setItem("editContactId", contact.id.toString());
    this.router.navigate(['edit-contact']);
  };

  deleteContact(contact:ContactDTO):void {
    this.contactService.deleteContact(contact).subscribe(data => {
      this.contacts = this.contacts.filter(c => c !== contact);
    });
  };

  getTrustedURL(url:string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
}
