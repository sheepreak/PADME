import {Component, OnInit} from '@angular/core';
import {ManageFile} from '../manageFile';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../user.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MedicalFileService} from '../medical-file.service';
import * as util from 'util';
import {Constants} from '../../constants';
import {TokenService} from '../token.service';

@Component({
  selector: 'app-consultation-file',
  templateUrl: './consultation-file.component.html',
  styleUrls: ['./consultation-file.component.css', './../app.component.css']
})
export class ConsultationFileComponent implements OnInit {
  consultation: any;
  manageFile: ManageFile = new ManageFile();

  userFirstName: string;
  userLastName: string;
  userStatus: string;
  patient: any;
  doctor: any = {};

  constructor(private medicalService: MedicalFileService, private router: Router, private route: ActivatedRoute, private userService: UserService, private http: HttpClient, private tokenService: TokenService) {
  }

  ngOnInit() {
    this.userFirstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.userLastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.userStatus = this.userService.getStatus() ? this.userService.getStatus() : '';
    this.patient = this.userService.getPatient();

    let state;
    this.route.params.subscribe(params => {
      state = params['state'];
      if (state == 'new') {
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.consultation = this.userService.getConsultation();
      }
    });

    if (this.manageFile.statePublish()) {
      this.medicalService.getDoctor(this.consultation.staffId).then(data => {
        this.doctor = data;
      });
    }

  }

  onSubmit(form) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    const req = this.http.put(util.format(Constants.ADD_OBSERVATION_URL, this.userService.getIdMedicalFolder()), {
      comment: form.comment,
      staffId: this.userService.getId()
    }, {headers: headers})
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log('Error occured');
        }
      );
    this.router.navigate(['doclist', {type: 'Consultation'}]);
  }
}
