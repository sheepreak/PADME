
export class Patient {
  firstname: string;
  lastname: string;

  constructor(firstname: string, lastname: string) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public setPatient(firstname: string, lastname: string){
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public getFirstName(){
    return this.firstname;
  }

  public getLastName(){
    return this.lastname;
  }
}
