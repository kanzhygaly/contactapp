import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ListContactComponent} from './list-contact/list-contact.component';
import {AddContactComponent} from './add-contact/add-contact.component';
import {EditContactComponent} from './edit-contact/edit-contact.component';

const routes:Routes = [
  {path: 'list-contact', component: ListContactComponent},
  {path: 'add-contact', component: AddContactComponent},
  {path: 'edit-contact', component: EditContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
