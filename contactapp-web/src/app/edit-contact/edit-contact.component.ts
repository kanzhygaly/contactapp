import { Component, OnInit } from '@angular/core';
import {first} from "rxjs/operators";
import {Router} from '@angular/router';
import {ContactDTO} from '../model/contact.model';
import {ContactService} from '../service/contact.service';
import {ContactDTO} from "../model/contact.model";

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.css']
})
export class EditContactComponent implements OnInit {

  contact:ContactDTO;

  constructor(private router:Router, private contactService:ContactService) {
  }

  ngOnInit() {
    let contactId = localStorage.getItem("editContactId");
    if (!contactId) {
      alert("Invalid action.")
      this.router.navigate(['list-contact']);
      return;
    }

    this.contactService.getContactById(contactId).subscribe((data : ContactDTO) => {
      this.contact = data;
    });
  }

  onSubmit() {
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
