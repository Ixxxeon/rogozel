import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Microservice } from 'app/shared/model/microservice.model';
import { MicroserviceService } from './microservice.service';
import { MicroserviceComponent } from './microservice.component';
import { MicroserviceDetailComponent } from './microservice-detail.component';
import { MicroserviceUpdateComponent } from './microservice-update.component';
import { IMicroservice } from 'app/shared/model/microservice.model';

@Injectable({ providedIn: 'root' })
export class MicroserviceResolve implements Resolve<IMicroservice> {
  constructor(private service: MicroserviceService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMicroservice> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((microservice: HttpResponse<Microservice>) => microservice.body));
    }
    return of(new Microservice());
  }
}

export const microserviceRoute: Routes = [
  {
    path: '',
    component: MicroserviceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Microservices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MicroserviceDetailComponent,
    resolve: {
      microservice: MicroserviceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Microservices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MicroserviceUpdateComponent,
    resolve: {
      microservice: MicroserviceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Microservices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MicroserviceUpdateComponent,
    resolve: {
      microservice: MicroserviceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Microservices'
    },
    canActivate: [UserRouteAccessService]
  }
];
