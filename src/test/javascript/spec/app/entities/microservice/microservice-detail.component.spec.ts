import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RogozelTestModule } from '../../../test.module';
import { MicroserviceDetailComponent } from 'app/entities/microservice/microservice-detail.component';
import { Microservice } from 'app/shared/model/microservice.model';

describe('Component Tests', () => {
  describe('Microservice Management Detail Component', () => {
    let comp: MicroserviceDetailComponent;
    let fixture: ComponentFixture<MicroserviceDetailComponent>;
    const route = ({ data: of({ microservice: new Microservice(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RogozelTestModule],
        declarations: [MicroserviceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MicroserviceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MicroserviceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.microservice).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
