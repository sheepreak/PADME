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
        console.log(i.name);
        this.img.push(i);
        this.img.push(ii);

        /*
        this.directory.description = 'Radiographie simple de l\'epaule gauche avec clicher de face stricte et profile auxillaire';
        this.directory.motif = 'Suite à une chute d\'équitation, douleur à l\'epaule droit, et manque de mobilité';
        this.directory.observation = 'Fracture tassement de la face posterieur de la tếte humérale';
        this.imgMin = true;

        let i = new Image('Radio épaule profile auxillaire', '../../assets/img/photo/epaule1.jpg');
        let ii = new Image('Radio épaule face stricte', '../../assets/img/photo/epaule2.png');
        console.log(i.name);
        this.img.push(i);
        this.img.push(ii);
        */
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
    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addexam/72', {
      motive: 'test',
      description : 'test',
      observation : 'test',
      date : 'test',
      staffId: 121
    })
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log("Error occured");
        }
      );

    this.router.navigate(['/doclist']);
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
