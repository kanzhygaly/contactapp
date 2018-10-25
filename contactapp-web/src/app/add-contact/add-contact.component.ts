import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from '@angular/router';
import {ContactService} from '../service/contact.service';

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent implements OnInit {

  addForm:FormGroup;

  constructor(private formBuilder:FormBuilder, private router:Router, private contactService:ContactService) {
  }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      photo: ['', Validators.required]
    });
  }

  onSubmit() {
    this.contactService.saveContact(this.addForm.value).subscribe(data => {
      this.router.navigate(['list-contact']);
    });
  }

  onCancel() {
    this.router.navigate(['list-contact']);
  }
}
