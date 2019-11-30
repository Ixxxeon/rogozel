import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.RogozelLocationModule)
      },
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.RogozelDepartmentModule)
      },
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.RogozelEmployeeModule)
      },
      {
        path: 'catalog',
        loadChildren: () => import('./catalog/catalog.module').then(m => m.RogozelCatalogModule)
      },
      {
        path: 'microservice',
        loadChildren: () => import('./microservice/microservice.module').then(m => m.RogozelMicroserviceModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class RogozelEntityModule {}
