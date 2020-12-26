
import { Person } from "./person";
import { Team } from "./team";

export class Event {
    id:number;
    place:string;
    availablePLaces:number;
    time:Date;
    persons:Person[];
    private _host: Team;

    private _guest: Team;
    public get host(): Team {
        return this._host;
    }
    public set host(value: Team) {
        this._host = value;
    }
    public get guest(): Team {
        return this._guest;
    }
    public set guest(value: Team) {
        this._guest = value;
    }
}
