import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMicroservice } from 'app/shared/model/microservice.model';

@Component({
  selector: 'jhi-microservice-detail',
  templateUrl: './microservice-detail.component.html'
})
export class MicroserviceDetailComponent implements OnInit {
  microservice: IMicroservice;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ microservice }) => {
      this.microservice = microservice;
    });
  }

  previousState() {
    window.history.back();
  }
}
