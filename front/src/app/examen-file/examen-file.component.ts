import {Component, OnInit} from '@angular/core';
import {Image} from '../image';
import {ManageFile} from '../manageFile';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../user.service';
import {WebApiPromiseService} from '../web-api-promise.service';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from '@angular/common/http';
import {MedicalFileService} from '../medical-file.service';
import * as util from 'util';
import {Constants} from '../../constants';
import {TokenService} from '../token.service';
import {ResponseContentType} from '@angular/http';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-examen-file',
  templateUrl: './examen-file.component.html',
  styleUrls: ['./examen-file.component.css', './../app.component.css']
})

export class ExamenFileComponent implements OnInit {
  examen: any = {};
  listImgSrc = [];

  // img: Array<Image> = [];


  listImg =[];

  imgMin: boolean;
  manageFile: ManageFile = new ManageFile();

  // nameImg: string;
  // pathImg: string;

  userFirstName: string;
  userLastName: string;
  status: string;
  patient: any = {};
  doctor: any = {};

  constructor(private medicalService: MedicalFileService, private router: Router, private route: ActivatedRoute,
              private userService: UserService, private requester: WebApiPromiseService, private http: HttpClient,
              private tokenService: TokenService) {
  }

  ngOnInit() {
    this.userFirstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.userLastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.status = this.userService.getStatus() ? this.userService.getStatus() : '';

    this.patient = this.userService.getPatient();
    console.log(this.patient);
    console.log(this.patient.firstName);

    this.route.params.subscribe(params => {
      if (params['state'] === 'new') {
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.examen = this.userService.getExamen();
        if (this.examen.imgPath != null) {
          for (let i = 0; i < this.examen.imgPath.length; i++) {
            this.listImgSrc.push(this.getImgFromServer(this.examen.imgPath[i]));
          }
        }
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
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });

    const req = this.http.put<{ idExamen }>(util.format(Constants.ADD_EXAM_URL, this.userService.getIdMedicalFolder()), {
      motive: form.motif,
      description: form.description,
      observation: form.description,
      date: Date.now().toString(),
      staffId: this.userService.getId()
    }, {headers: headers})
      .subscribe(
        res => {
          console.log('abcd');
          console.log(res);
          this.postImg(res.idExamen);
          this.router.navigate(['doclist', {type: 'Examen'}]);
        },
        err => {
          console.log('Error occured');
        }
      );
  }


  public postImg(idExamen) {
    const headerImg = new HttpHeaders({
      'Content-Type': 'image/png',
      'Authorization': this.tokenService.getToken(),
    });

    for (let i = 0; i < this.listImg.length; i++) {
      this.http.post(util.format(Constants.ADD_IMAGE_MEDICAL_URL, idExamen), this.listImg[i],
        {headers: headerImg})
        .subscribe(data => {
          console.log(data);
        }, err => {
          console.log(err);
        });

    }
  }


  // public changeImg(img: Image) {
  //   img.changeImg();
  // }
  //
  // public loadAllImg() {
  //   for (let i of this.img) {
  //     i.loadImg();
  //   }
  // }
  //
  // public addImg() {
  //   if (this.nameImg != null && this.pathImg != null) {
  //     let i = new Image(this.nameImg, this.pathImg);
  //     this.img.push(i);
  //
  //     this.pathImg = null;
  //     this.nameImg = null;
  //   }
  // }


  public onChangeImg(event) {
    this.listImg = event.target.files;
  }


  public getImgFromServer(name) {
    const headerImg = new HttpHeaders({
      'Content-Type': 'image/*',
      'Authorization': this.tokenService.getToken(),
    });

    // this.http.get(util.format(Constants.GET_IMAGE_MEDICAL_URL, name),
    //   {responseType: 'blob', headers: headerImg}).subscribe(res => {
    //   this.isloading = true;
    //   this.test = res;
    //
    // }, error2 => {
    //   console.log(error2);
    // });
    return util.format(Constants.GET_IMAGE_MEDICAL_URL, name);
  }

}
