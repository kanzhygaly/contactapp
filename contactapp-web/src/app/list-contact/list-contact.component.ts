import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ContactDTO} from '../model/contact.model';
import {ContactService} from '../service/contact.service';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-list-contact',
  templateUrl: './list-contact.component.html',
  styleUrls: ['./list-contact.component.css']
})
export class ListContactComponent implements OnInit {

  contacts:Array<ContactDTO>;

  constructor(private router:Router, private contactService:ContactService, private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.contactService.getContacts().subscribe((data : Array<ContactDTO>) => {
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

  getTrustedURL(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
}
