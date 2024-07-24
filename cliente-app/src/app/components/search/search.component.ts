import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteService } from '../../services/cliente.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Importa FormsModule y ReactiveFormsModule
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  tipo: string = '';
  numero: string = '';

  constructor(private clienteService: ClienteService, private router: Router) { }

  onSubmit() {
    this.clienteService.getCliente(this.tipo, this.numero).subscribe({
      next: (data) => {
        this.router.navigate(['/summary'], { state: { data } });
      },
      error: (err) => {
        alert('Error: ' + err.error);
      }
    });
  }

  formatNumber() {
    // Formatear número con separadores de miles
    let num = this.numero.replace(/\D/g, '');
    this.numero = num.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  }
}
