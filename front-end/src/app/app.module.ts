import { Injectable, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app.routes';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { CurrencyPipe, DatePipe, DecimalPipe } from '@angular/common';
import { AppComponent } from './app.component';
import {MatTableModule } from '@angular/material/table';
import {MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { FormsModule,ReactiveFormsModule }   from '@angular/forms';

@Injectable()
export class customPaginatorLabel extends MatPaginatorIntl{
/*   override itemsPerPageLabel = 'Registros por página'; 
  override nextPageLabel = 'Siguiente página';
  override previousPageLabel = 'Página anterior';
  override firstPageLabel = 'Primera página';
  override lastPageLabel = 'Última página'; */
}

@NgModule({
    declarations: [AppComponent],
    imports: [BrowserModule,AppRoutingModule,HttpClientModule,MatTableModule,MatPaginatorModule,BrowserAnimationsModule,MatIconModule,FormsModule,ReactiveFormsModule],
    providers: [DatePipe,{ provide: MAT_DATE_LOCALE, useValue: 'es-CL' },
    DecimalPipe,CurrencyPipe,{ provide: MatDialogRef, useValue: {} },{provide: MatPaginatorIntl, useClass:customPaginatorLabel}],
    bootstrap: [AppComponent]
})

export class AppModule { }