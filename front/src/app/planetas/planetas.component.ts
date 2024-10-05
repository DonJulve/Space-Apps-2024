import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule} from '@angular/common';

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
    console.log(this.lente);
  }
  moveLeft() {
    console.log('Moved left');
  }

  moveRight() {
    console.log('Moved right');
  }
}
