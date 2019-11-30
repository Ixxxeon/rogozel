import { ICatalog } from 'app/shared/model/catalog.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IMicroservice {
  id?: number;
  name?: string;
  status?: Status;
  catalog?: ICatalog;
}

export class Microservice implements IMicroservice {
  constructor(public id?: number, public name?: string, public status?: Status, public catalog?: ICatalog) {}
}
