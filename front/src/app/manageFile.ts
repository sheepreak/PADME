export class ManageFile
{
  constructor ()
  {
    this.state = ManageFile.State.Consulted;
  }
  state: ManageFile.State;
  hopital: string
  date: string

  publishData(){
    var today = new Date();
    var jj = today.getDay().toString();
    var mm = (today.getMonth()+1).toString();
    var aaaa = today.getFullYear();

    if (jj.length != 2){
      jj = "0".concat(jj);
    }
    if (mm.length != 2){
      mm = "0".concat(mm);
    }

    this.date = jj+"/"+mm+"/"+aaaa;
    this.hopital = "Hopital de Paris";
    this.state = ManageFile.State.Publish;
  }

  stateConsulted(){
    return this.state == ManageFile.State.Consulted;
  }

  stateEdited(){
    return this.state == ManageFile.State.Edited;
  }

  statePublish(){
    return this.state == ManageFile.State.Publish;
  }
}

export namespace ManageFile
{
  export enum State
  {
    Consulted,
    Edited,
    Publish
  }
}
