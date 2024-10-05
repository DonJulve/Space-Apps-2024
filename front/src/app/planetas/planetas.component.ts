import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-planetas',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './planetas.component.html',
  styleUrls: ['./planetas.component.css']
})
export class PlanetasComponent {
  menuVisible = false;
  lente = '';
  habitabilidad = '';
  distanciaTierra = '';
  proporcionGravedad = '';
  exoplanetas: any[] = [];
  currentIndex = 0; // Índice del exoplaneta actual
  url: SafeResourceUrl; // Cambiar el tipo a SafeResourceUrl

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>; // Referencia al elemento de audio

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {
    // Marcar la URL como segura en el constructor
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl('https://eyes.nasa.gov/apps/exo/#/planet/HIP_65_A_b');
  }

  ngOnInit() {
    this.exoplanetas = [
      { habitabilidad: 0.042747900455402776, name: "HIP_65_A_b", gravity: 1.315575993334133E-10, distanceToStar: 61.7856, yearDuration: 0.002685758795345654 },
      { habitabilidad: 0.05868514370251443, name: "WASP-140_b", gravity: 1.9854075686038596E-10, distanceToStar: 117.598, yearDuration: 0.006121789185489391 },
      { habitabilidad: 0.0597743486645288, name: "HD_209458_b", gravity: 6.374599829669994E-11, distanceToStar: 48.3016, yearDuration: 0.009650235701574264 },
      { habitabilidad: 0.06068415543329542, name: "KELT-11_b", gravity: 1.583157661429539E-11, distanceToStar: 99.1596, yearDuration: 0.012966735112936346 },
      { habitabilidad: 0.06591975029491937, name: "WASP-77_A_b", gravity: 1.8591506968303817E-10, distanceToStar: 105.166, yearDuration: 0.003723555208761123 },
      { habitabilidad: 0.0691581557541493, name: "BD-210397_c", gravity: 6.114773318016945E-10, distanceToStar: 23.7324, yearDuration: 17.084188911704313 },
      { habitabilidad: 0.07178902357803207, name: "KELT-24_b", gravity: 6.022430448888376E-10, distanceToStar: 96.5173, yearDuration: 0.01519915937029432 },
      { habitabilidad: 0.07249131948831378, name: "HAT-P-22_b", gravity: 3.1514602987262875E-10, distanceToStar: 81.7647, yearDuration: 0.008794579055441478 },
      { habitabilidad: 0.07265450016998065, name: "HD_118203_b", gravity: 2.935006333114788E-10, distanceToStar: 92.2589, yearDuration: 0.016796684462696783 },
      { habitabilidad: 0.07329950577747346, name: "HD 189733 b", gravity: 1.493207621388934E-10, distanceToStar: 19.7638, yearDuration: 0.0060741291444216285 }
    ];
    this.updateExoplanet(); // Llama al método para establecer la URL inicial
  }

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

    // Aquí puedes realizar la solicitud HTTP para enviar los datos
    // Ejemplo: this.http.post('tu_url_api', data, { headers }).subscribe(...);
  }

  moveLeft() {
    if (this.currentIndex > 0) {
      this.currentIndex--;
      this.updateExoplanet();
    }
  }

  moveRight() {
    if (this.currentIndex < this.exoplanetas.length - 1) {
      this.currentIndex++;
      this.updateExoplanet();
    }
  }

  updateExoplanet() {
    const currentExoplanet = this.exoplanetas[this.currentIndex];

    if (currentExoplanet) {
      console.log(`Current Exoplanet: ${currentExoplanet.name}`); // Loguear el nombre del exoplaneta actual
      this.url = this.sanitizer.bypassSecurityTrustResourceUrl(`https://eyes.nasa.gov/apps/exo/#/planet/${currentExoplanet.name}`);
    }
  }

  reproducirAudio() {
    let audioFile = '';
    const currentExoplanet = this.exoplanetas[this.currentIndex];

    switch (currentExoplanet?.name) {
      case 'HIP_65_A_b':
        audioFile = 'assets/audio1.mp3';
        break;
      case 'WASP-140_b':
        audioFile = 'assets/audio2.mp3';
        break;
      case 'KELT-11_b':
        audioFile = 'assets/audio3.mp3';
        break;
      default:
        console.log('No hay audio para este exoplaneta.');
        return; // Salir si no hay audio correspondiente
    }

    if (this.audioPlayer && this.audioPlayer.nativeElement) {
      this.audioPlayer.nativeElement.src = audioFile; // Asignar la ruta del audio
      this.audioPlayer.nativeElement.play(); // Reproducir el audio
    } else {
      console.error('El audioPlayer no está inicializado');
    }
  }
}
