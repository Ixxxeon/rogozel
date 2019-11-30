import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Catalog } from 'app/shared/model/catalog.model';
import { CatalogService } from './catalog.service';
import { CatalogComponent } from './catalog.component';
import { CatalogDetailComponent } from './catalog-detail.component';
import { CatalogUpdateComponent } from './catalog-update.component';
import { ICatalog } from 'app/shared/model/catalog.model';

@Injectable({ providedIn: 'root' })
export class CatalogResolve implements Resolve<ICatalog> {
  constructor(private service: CatalogService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICatalog> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((catalog: HttpResponse<Catalog>) => catalog.body));
    }
    return of(new Catalog());
  }
}

export const catalogRoute: Routes = [
  {
    path: '',
    component: CatalogComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Catalogs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CatalogDetailComponent,
    resolve: {
      catalog: CatalogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Catalogs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CatalogUpdateComponent,
    resolve: {
      catalog: CatalogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Catalogs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CatalogUpdateComponent,
    resolve: {
      catalog: CatalogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Catalogs'
    },
    canActivate: [UserRouteAccessService]
  }
];
