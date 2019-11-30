import { IMicroservice } from 'app/shared/model/microservice.model';

export interface ICatalog {
  id?: number;
  name?: string;
  microservices?: IMicroservice[];
}

export class Catalog implements ICatalog {
  constructor(public id?: number, public name?: string, public microservices?: IMicroservice[]) {}
}
