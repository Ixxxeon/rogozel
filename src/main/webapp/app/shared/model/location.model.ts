export interface ILocation {
  id?: number;
  streetAddress?: string;
  city?: string;
  house?: string;
  flat?: string;
}

export class Location implements ILocation {
  constructor(public id?: number, public streetAddress?: string, public city?: string, public house?: string, public flat?: string) {}
}
