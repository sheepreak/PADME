import {Component, OnInit} from '@angular/core';
import {ManageFile} from "../manageFile";
import {Patient} from "../patient";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {WebApiPromiseService} from "../web-api-promise.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-consultation-file',
  templateUrl: './consultation-file.component.html',
  styleUrls: ['./consultation-file.component.css', './../app.component.css']
})
export class ConsultationFileComponent implements OnInit {
  directory = {
    motif: '',
    observation: ''
  }
  oldDirectory;
  manageFile: ManageFile = new ManageFile();

  userFirstName: string;
  userLastName: string;
  userStatus: string;

  patientFirstName: string;
  patientLastName: string;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private http: HttpClient) {
  }

  ngOnInit() {
    this.userFirstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.userLastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.userStatus = this.userService.getStatus() ? this.userService.getStatus() : '';

    this.patientFirstName = this.userService.getPatient().firstName;
    this.patientLastName = this.userService.getPatient().lastName;

    let state;
    this.route.params.subscribe(params => {
      state = params['state'];
      if (state == 'new') {
        this.userService.resetConsultation();
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.directory.motif = this.userService.getConsultation().comment;
        this.directory.observation = this.userService.getConsultation().comment;
      }
    });
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

    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addobservation/72', {
      comment: form.comment,
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
    this.router.navigate(['doclist', { type: 'Consultation' }]);
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
}
