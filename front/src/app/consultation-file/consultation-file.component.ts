import {Component, OnInit} from '@angular/core';
import {ManageFile} from "../manageFile";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {HttpClient} from "@angular/common/http";

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

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private http: HttpClient) {
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
    });statePublish
  }

  onSubmit(form) {
    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addobservation/'  + this.userService.getIdMedicalFolder(), {
      comment: form.comment,
      date: new Date().toDateString(),
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
    this.router.navigate(['doclist', {type: 'Consultation'}]);
  }
}
