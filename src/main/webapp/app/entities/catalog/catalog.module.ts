import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RogozelSharedModule } from 'app/shared/shared.module';
import { CatalogComponent } from './catalog.component';
import { CatalogDetailComponent } from './catalog-detail.component';
import { CatalogUpdateComponent } from './catalog-update.component';
import { CatalogDeleteDialogComponent } from './catalog-delete-dialog.component';
import { catalogRoute } from './catalog.route';

@NgModule({
  imports: [RogozelSharedModule, RouterModule.forChild(catalogRoute)],
  declarations: [CatalogComponent, CatalogDetailComponent, CatalogUpdateComponent, CatalogDeleteDialogComponent],
  entryComponents: [CatalogDeleteDialogComponent]
})
export class RogozelCatalogModule {}
