import { Routes } from '@angular/router';
import { PaginaPrincipalComponent } from './pagina-principal/pagina-principal.component';
import { PlanetasComponent } from './planetas/planetas.component';

export const routes: Routes = [
    { path: '', title: 'Space Apps', component: PaginaPrincipalComponent },
    { path: 'planetas', title: 'Planetas', component: PlanetasComponent },
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
