import {Component, OnInit} from '@angular/core';
import {Image} from '../image';
import {ManageFile} from '../manageFile';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {WebApiPromiseService} from "../web-api-promise.service";
import {HttpClient, HttpRequest} from "@angular/common/http";

@Component({
  selector: 'app-examen-file',
  templateUrl: './examen-file.component.html',
  styleUrls: ['./examen-file.component.css', './../app.component.css']
})

export class ExamenFileComponent implements OnInit {
  directory = {
    motif: '',
    description: '',
    result: '',
    observation: ''
  };
  oldDirectory;

  img: Array<Image> = [];
  imgMin: boolean;
  manageFile: ManageFile = new ManageFile();

  firstName: string;
  lastName: string;
  status: string;
  nameImg: string;
  pathImg: string;

  patientFirstName: string;
  patientLastName: string

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService,  private requester: WebApiPromiseService, private http: HttpClient) {
  }

  ngOnInit() {
    this.firstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.lastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.status = this.userService.getStatus() ? this.userService.getStatus() : '';

    this.patientFirstName = this.userService.getPatient().firstName;
    this.patientLastName = this.userService.getPatient().lastName;

    let state;
    this.route.params.subscribe(params => {
      state = params['state'];

      if (state == 'new') {
        this.userService.resetExamen();
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.directory.description = this.userService.getExamen().description;
        this.directory.motif = this.userService.getExamen().motive;;
        this.directory.observation = this.userService.getExamen().description;
        this.imgMin = true;

        let i = new Image('Radio épaule profile auxillaire', '../../assets/img/photo/epaule1.jpg');
        let ii = new Image('Radio épaule face stricte', '../../assets/img/photo/epaule2.png');
        this.img.push(i);
        this.img.push(ii);
      }
    });
  }

  modifData() {
    this.oldDirectory = Object.assign({}, this.directory);
    this.manageFile.state = ManageFile.State.Edited;
  }

  cancelModif() {
    console.log(this.oldDirectory);
    this.directory = this.oldDirectory;
    this.manageFile.state = ManageFile.State.Consulted;
  }

  onSubmit(form) {
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

    let date = aaaa+"-"+mm+"-"+jj;

    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addexam/72', {
      motive: form.motif,
      description : form.description,
      observation : form.description,
      date : date,
      staffId: this.userService.getId()
    })
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log("Error occured");
        }
      );
    this.router.navigate(['doclist', { type: 'Examen' }]);
  }

  public changeImg(img: Image) {
    img.changeImg();
  }

  public loadAllImg() {
    for (let i of this.img) {
      i.loadImg();
    }
  }

  public addImg() {
    if (this.nameImg != null && this.pathImg != null) {

      let i = new Image(this.nameImg, this.pathImg);
      this.img.push(i);

      this.pathImg = null;
      this.nameImg = null;
    }
  }
}
