import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICatalog } from 'app/shared/model/catalog.model';
import { CatalogService } from './catalog.service';
import { CatalogDeleteDialogComponent } from './catalog-delete-dialog.component';

@Component({
  selector: 'jhi-catalog',
  templateUrl: './catalog.component.html'
})
export class CatalogComponent implements OnInit, OnDestroy {
  catalogs: ICatalog[];
  eventSubscriber: Subscription;

  constructor(protected catalogService: CatalogService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.catalogService.query().subscribe((res: HttpResponse<ICatalog[]>) => {
      this.catalogs = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInCatalogs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICatalog) {
    return item.id;
  }

  registerChangeInCatalogs() {
    this.eventSubscriber = this.eventManager.subscribe('catalogListModification', () => this.loadAll());
  }

  delete(catalog: ICatalog) {
    const modalRef = this.modalService.open(CatalogDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.catalog = catalog;
  }
}
