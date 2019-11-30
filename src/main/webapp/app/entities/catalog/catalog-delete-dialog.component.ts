import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatalog } from 'app/shared/model/catalog.model';
import { CatalogService } from './catalog.service';

@Component({
  templateUrl: './catalog-delete-dialog.component.html'
})
export class CatalogDeleteDialogComponent {
  catalog: ICatalog;

  constructor(protected catalogService: CatalogService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.catalogService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'catalogListModification',
        content: 'Deleted an catalog'
      });
      this.activeModal.dismiss(true);
    });
  }
}
