import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RogozelTestModule } from '../../../test.module';
import { MicroserviceDeleteDialogComponent } from 'app/entities/microservice/microservice-delete-dialog.component';
import { MicroserviceService } from 'app/entities/microservice/microservice.service';

describe('Component Tests', () => {
  describe('Microservice Management Delete Component', () => {
    let comp: MicroserviceDeleteDialogComponent;
    let fixture: ComponentFixture<MicroserviceDeleteDialogComponent>;
    let service: MicroserviceService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RogozelTestModule],
        declarations: [MicroserviceDeleteDialogComponent]
      })
        .overrideTemplate(MicroserviceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MicroserviceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MicroserviceService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
