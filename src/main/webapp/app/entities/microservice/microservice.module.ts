import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RogozelSharedModule } from 'app/shared/shared.module';
import { MicroserviceComponent } from './microservice.component';
import { MicroserviceDetailComponent } from './microservice-detail.component';
import { MicroserviceUpdateComponent } from './microservice-update.component';
import { MicroserviceDeleteDialogComponent } from './microservice-delete-dialog.component';
import { microserviceRoute } from './microservice.route';

@NgModule({
  imports: [RogozelSharedModule, RouterModule.forChild(microserviceRoute)],
  declarations: [MicroserviceComponent, MicroserviceDetailComponent, MicroserviceUpdateComponent, MicroserviceDeleteDialogComponent],
  entryComponents: [MicroserviceDeleteDialogComponent]
})
export class RogozelMicroserviceModule {}
