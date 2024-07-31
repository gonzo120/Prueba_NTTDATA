import { Component, OnInit, Inject } from '@angular/core';
import { ClienteService } from '../../services/cliente.service';
import { Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-summary',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, HttpClientModule, CommonModule],
  templateUrl: './summary.component.html',
  styleUrl: './summary.component.css'
})
export class SummaryComponent {
  tipo: string = '';
  Numero: string = '';
  cliente: any ;

  showPrimerNombre = true;
  showPrimerApellido = true;
  showSegundoApellido = true;
  showTelefono = true;


  constructor(private clienteService: ClienteService,
     private router: Router,
     @Inject(MAT_DIALOG_DATA) public data: any,
     public dialogRef: MatDialogRef<SummaryComponent>) { 
      this.cliente = data;
      console.log(this.cliente);
     }
  ngOnInit(): void {
  }
  
  async onSubmit() {
    try {
      this.Numero = this.cliente.cedula;
      const actualizado = await this.clienteService.updateCliente(this.Numero, this.cliente).toPromise();
      console.log(actualizado);
      if (actualizado != null) {
        this.goBack();
      }
    } catch (error) {
      console.error('Error al actualizar el cliente:', error);
    }
  }
  goBack() {
    this.dialogRef.close();

  }
}
