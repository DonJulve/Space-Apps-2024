import { Component } from '@angular/core'
import { FormsModule } from '@angular/forms';
import { CommonModule} from '@angular/common';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-planetas',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './planetas.component.html',
  styleUrl: './planetas.component.css'
})
export class PlanetasComponent {
  

  menuVisible = false;
  lente = '';
  habitabilidad = '';
  distanciaTierra = '';
  proporcionGravedad = '';

  constructor(private http: HttpClient) {}

  alternarMenu() {
    this.menuVisible = !this.menuVisible;
  }
  limpiar() {
    this.lente = '';
    this.habitabilidad = '';
    this.distanciaTierra = '';
    this.proporcionGravedad = '';
  }

  submit() {
    const data = {
      diametroLente: this.lente,
      habitabilidad: this.habitabilidad,
      distanciaTierra: this.distanciaTierra,
      proporcionGravedad: this.proporcionGravedad
    };
    
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    this.http.post<any>('https://localhost:8080/getExoplanets', data, { headers: headers })
    .subscribe(
      response => {
        // Si la autenticación fue exitosa, muestra una alerta con el token.
        alert(response);
      },
      error => {
        // Si hubo un error durante la autenticación, muestra una alerta con el mensaje de error.
        console.error('Error al autenticar', error);
      }
    );
}

  moveLeft() {
    console.log('Moved left');
  }

  moveRight() {
    console.log('Moved right');
  }
}
