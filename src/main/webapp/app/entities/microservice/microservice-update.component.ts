import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IMicroservice, Microservice } from 'app/shared/model/microservice.model';
import { MicroserviceService } from './microservice.service';
import { ICatalog } from 'app/shared/model/catalog.model';
import { CatalogService } from 'app/entities/catalog/catalog.service';

@Component({
  selector: 'jhi-microservice-update',
  templateUrl: './microservice-update.component.html'
})
export class MicroserviceUpdateComponent implements OnInit {
  isSaving: boolean;

  catalogs: ICatalog[];

  editForm = this.fb.group({
    id: [],
    name: [],
    status: [],
    catalog: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected microserviceService: MicroserviceService,
    protected catalogService: CatalogService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ microservice }) => {
      this.updateForm(microservice);
    });
    this.catalogService
      .query()
      .subscribe((res: HttpResponse<ICatalog[]>) => (this.catalogs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(microservice: IMicroservice) {
    this.editForm.patchValue({
      id: microservice.id,
      name: microservice.name,
      status: microservice.status,
      catalog: microservice.catalog
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const microservice = this.createFromForm();
    if (microservice.id !== undefined) {
      this.subscribeToSaveResponse(this.microserviceService.update(microservice));
    } else {
      this.subscribeToSaveResponse(this.microserviceService.create(microservice));
    }
  }

  private createFromForm(): IMicroservice {
    return {
      ...new Microservice(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      status: this.editForm.get(['status']).value,
      catalog: this.editForm.get(['catalog']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMicroservice>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCatalogById(index: number, item: ICatalog) {
    return item.id;
  }
}
