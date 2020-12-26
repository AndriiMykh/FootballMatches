
import { Person } from "./person";
import { Team } from "./team";

export class Event {
    id:number;
    place:string;
    availablePLaces:number;
    time:Date;
    persons:Person[];
    host:Team;
    guest:Team;
}
