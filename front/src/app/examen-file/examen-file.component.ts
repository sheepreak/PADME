import {Component, OnInit} from '@angular/core';
import {Image} from '../image';
import {ManageFile} from '../manageFile';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {WebApiPromiseService} from "../web-api-promise.service";
import {HttpClient, HttpRequest} from "@angular/common/http";
import {MedicalFileService} from "../medical-file.service";

@Component({
  selector: 'app-examen-file',
  templateUrl: './examen-file.component.html',
  styleUrls: ['./examen-file.component.css', './../app.component.css']
})

export class ExamenFileComponent implements OnInit {
  examen: any;

  img: Array<Image> = [];
  imgMin: boolean;
  manageFile: ManageFile = new ManageFile();

  nameImg: string;
  pathImg: string;

  userFirstName: string;
  userLastName: string;
  status: string;
  patient: any;
  doctor:any;

  constructor(private medicalService: MedicalFileService, private router: Router, private route: ActivatedRoute, private userService: UserService, private requester: WebApiPromiseService, private http: HttpClient) {
  }

  ngOnInit() {
    this.userFirstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.userLastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.status = this.userService.getStatus() ? this.userService.getStatus() : '';

    this.patient = this.userService.getPatient();

    this.route.params.subscribe(params => {
      if (params['state'] == 'new') {
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.examen = this.userService.getExamen();
        this.imgMin = true;
      }
    });

    if (this.manageFile.statePublish()) {
      this.medicalService.getDoctor(this.examen.staffId).then(data => {
        this.doctor = data;
      });
    }
  }

  onSubmit(form) {
    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addexam/'  + this.userService.getIdMedicalFolder(), {
      motive: form.motif,
      description: form.description,
      observation: form.description,
      date: Date.now().toString(),
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

    this.router.navigate(['doclist', {type: 'Examen'}]);
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
