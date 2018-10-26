import {Component, OnDestroy, OnInit} from '@angular/core';
import {first} from "rxjs/operators";
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {ContactService} from '../service/contact.service';
import {ContactDTO} from "../model/contact.model";

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.css']
})
export class EditContactComponent implements OnInit, OnDestroy {

  subscription:Subscription;
  contact:ContactDTO = new ContactDTO();

  constructor(private router:Router, private contactService:ContactService) {
  }

  ngOnInit() {
    let contactId = localStorage.getItem("editContactId");
    if (!contactId) {
      alert("Invalid action.")
      this.router.navigate(['list-contact']);
      return;
    }

    this.subscription = this.contactService.getContactById(contactId).subscribe((data:ContactDTO) => {
      this.contact = data;
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onSubmit() {
    console.log(this.contact)

    this.contactService.saveContact(this.contact).pipe(first()).subscribe(
      data => {
        this.router.navigate(['list-contact']);
      },
      error => {
        alert(error);
      });
  }

  onCancel() {
    this.router.navigate(['list-contact']);
  }
}
