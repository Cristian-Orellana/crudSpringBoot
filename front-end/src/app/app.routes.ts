import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

export const routes: Routes = [];

@NgModule({
    imports: [
      RouterModule.forRoot(routes),
      MatInputModule,
      MatButtonModule
    ],
    exports: [
      RouterModule,
      MatInputModule,
      MatButtonModule
    ]
  })

  export class AppRoutingModule { 
    
  }