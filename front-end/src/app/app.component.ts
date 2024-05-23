import { Component, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { ClientesService } from '../endPoints/clientes.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { lastValueFrom } from 'rxjs';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})


export class AppComponent {

  constructor(public ClientesService:ClientesService){}

  title = 'crud-spring-boot';
  dataClients:any = [];
  mapClients:any;
  columnsClients: string[] = ['rut', 'fist_name', 'last_name_father', 'last_name_mother','email','cellphone','status','options'];
  @ViewChild('pageClients') pageClients: MatPaginator | undefined;
  titleModal:string = 'Create Client';

  formClient = new FormGroup({
    id             :new FormControl(),
    rut            :new FormControl(),
    firstName      :new FormControl(),
    lastNameFather :new FormControl(),
    lastNameMother :new FormControl(),
    email          :new FormControl(),
    cellphone      :new FormControl(),
    status         :new FormControl(),
  });

  ngOnInit(){
    this.searchClients();
  }

  searchClients(){
    this.ClientesService.personFindAll().subscribe(resp => {
      console.log('resp : ',resp)
      this.mapClients = resp.data;
      this.loadTableClients();
    });
  }

  
	FilterClient(event: Event): void {
		const filterValue       = (event.target as HTMLInputElement).value;
		this.dataClients.filter = filterValue.trim().toLowerCase();
	}
	loadTableClients() {
		this.dataClients           = new MatTableDataSource(this.mapClients);
		this.dataClients.data      = this.mapClients;
		this.dataClients.paginator = this.pageClients;

	}

  clearForm(){
    this.formClient.get('rut')?.setValue('');
    this.formClient.get('rut')?.enable();
    this.formClient.get('firstName')?.setValue('');
    this.formClient.get('lastNameFather')?.setValue('');
    this.formClient.get('lastNameMother')?.setValue('');
    this.formClient.get('email')?.setValue('');
    this.formClient.get('cellphone')?.setValue('');
    this.formClient.get('id')?.setValue('');
    let statusEnabled = document.getElementById('statusEnabled') as HTMLInputElement;
    statusEnabled.checked = true;
    const rut = document.getElementById('rutForm') as HTMLInputElement;
    rut.className = "peer w-full h-full bg-transparent text-blue-gray-700 font-sans font-normal outline outline-0 "+
    "focus:outline-0 disabled:bg-blue-gray-50 disabled:border-0 transition-all placeholder-shown:borderplaceholder-shown:border-blue-gray-200 "+
    "placeholder-shown:border-t-blue-gray-200 border focus:border-2 border-t-transparent focus:border-t-transparent text-sm px-3 "+
    "py-2.5 rounded-[7px] border-blue-gray-200 focus:border-blue-900";

  }

  openModal(name:string,type:number,data:any) {
    if(type == 1){
      this.titleModal = 'Create Client';
      this.clearForm();
    }else{
      this.titleModal = 'Update Client';

      this.formClient.get('rut')?.setValue(data.rut);
      this.formClient.get('rut')?.disable();
      //class="mb-6 bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500" 
      const rut = document.getElementById('rutForm') as HTMLInputElement;
      rut.className = "mb-6 bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500";
      
      this.formClient.get('firstName')?.setValue(data.firstName);
      this.formClient.get('lastNameFather')?.setValue(data.lastNameFather);
      this.formClient.get('lastNameMother')?.setValue(data.lastNameMother);
      this.formClient.get('email')?.setValue(data.email);
      this.formClient.get('cellphone')?.setValue(data.cellphone);
      this.formClient.get('id')?.setValue(data.id);
      let statusEnabled = document.getElementById('statusEnabled') as HTMLInputElement;
      let statusDisabled = document.getElementById('statusDisabled') as HTMLInputElement;

      if(data.status == true){
        statusEnabled.checked = true;
      }else{
        statusDisabled.checked = true;
      }
    }
    console.log('data openModal : ', data)
    let myModal =  document.getElementById(''+name) as HTMLInputElement;
    myModal.classList.remove('hidden');
  }

  deleteClient(data:any){

    Swal.fire({
      title: "Are you sure to delete the client "+data.firstName+" "+data.lastNameFather+" "+data.lastNameMother+" ?",
      showCancelButton: true,
    }).then(async (result) => {
      if (result.isConfirmed) {
        try {
          const res = await lastValueFrom(this.ClientesService.personDelete(data.id));
          console.log('personDelete res : ',res)
    
          Swal.fire({
            title: "",
            text: res.message,
            showDenyButton: false,
            showCancelButton: false,
            confirmButtonText: "Ok",
            icon: "success"
          }).then((result) => {
            if (result.isConfirmed) {
              this.searchClients();
            }
          });
          
        }catch (error: any) {
          console.error('personDelete error :',error)
          Swal.fire({
            title: "",
            text: error.error.message,
            icon: "error"
          });
        }
      } else if (result.isDenied) {
        
      }
    });
  }

  closeModal(name:string) {
    let myModal =  document.getElementById(''+name) as HTMLInputElement;
    myModal.classList.add('hidden');
  }

  async createClient(){

    let statusEnabled = document.getElementById('statusEnabled') as HTMLInputElement;
    let statusClient:boolean = false;

    if(statusEnabled.checked == true){
      statusClient = true;
    }

    if(this.formClient.get('id')?.value == '' || this.formClient.get('id')?.value == null){
      
      let jsonSend = {    
        rut:            this.formClient.get('rut')?.value,
        firstName:      this.formClient.get('firstName')?.value,
        lastNameFather: this.formClient.get('lastNameFather')?.value,
        lastNameMother: this.formClient.get('lastNameMother')?.value,
        email:          this.formClient.get('email')?.value,
        cellphone:      this.formClient.get('cellphone')?.value,
        status:         statusClient
      }
      console.log('send createClient JSON : ',jsonSend)
      try {
        const res = await lastValueFrom(this.ClientesService.personSave(jsonSend));
        console.log('personSave res : ',res)
  
        Swal.fire({
          title: "",
          text: res.message,
          showDenyButton: false,
          showCancelButton: false,
          confirmButtonText: "Ok",
          icon: "success"
        }).then((result) => {
          if (result.isConfirmed) {
            this.searchClients();
            this.clearForm();
          }
        });
        
      }catch (error: any) {
        console.error('personSave error :',error)
        Swal.fire({
          title: "",
          text: error.error.message,
          icon: "error"
        });
      }
    }else{
      let jsonSend = {    
        id:             this.formClient.get('id')?.value,
        rut:            this.formClient.get('rut')?.value,
        firstName:      this.formClient.get('firstName')?.value,
        lastNameFather: this.formClient.get('lastNameFather')?.value,
        lastNameMother: this.formClient.get('lastNameMother')?.value,
        email:          this.formClient.get('email')?.value,
        cellphone:      this.formClient.get('cellphone')?.value,
        status:         statusClient
      }

      console.log('send updateClient JSON : ',jsonSend)
      try {
        const res = await lastValueFrom(this.ClientesService.personUpdate(jsonSend));
        console.log('personUpdate res : ',res)
  
        Swal.fire({
          title: "",
          text: res.message,
          showDenyButton: false,
          showCancelButton: false,
          confirmButtonText: "Ok",
          icon: "success"
        }).then((result) => {
          if (result.isConfirmed) {
            this.searchClients();
          }
        });
        
      }catch (error: any) {
        console.error('personUpdate error :',error)
        Swal.fire({
          title: "",
          text: error.error.message,
          icon: "error"
        });
      }
    }

  }

}




