

export class ManageFile
{
  constructor ()
  {
      this.state = ManageFile.State.Publish;
  }

  state: ManageFile.State;
  hopital: string
  date: string

  publishData(){
    this.date = dateFormatted1();
    this.hopital = "Hopital de Paris";
    this.state = ManageFile.State.Publish;
  }

  stateEdited(){
    return this.state == ManageFile.State.Edited;
  }

  statePublish(){
    return this.state == ManageFile.State.Publish;
  }

  stateNew(){
    return this.state == ManageFile.State.New;
  }
}

export namespace ManageFile
{
  export enum State
  {
    Edited,
    Publish,
    New
  }
}
