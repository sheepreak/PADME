import {Component, OnInit} from '@angular/core';
import {ManageFile} from "../manageFile";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-prescription-file',
  templateUrl: './prescription-file.component.html',
  styleUrls: ['./prescription-file.component.css', './../app.component.css']
})
export class PrescriptionFileComponent implements OnInit {
  prescription: any;

  posologies = [
    {date: "11h",
    observation: "Le patient va bien",
    Infirmier: "Ameline MOREAU"},
    {date: "13h",
      observation: "Le patient va toujour bien",
      Infirmier: "Ameline MOREAU"}
  ]

  manageFile: ManageFile = new ManageFile();
  userFirstName: string;
  userLastName: string;
  status: string;

  patient: any;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private http: HttpClient) {
  }

  ngOnInit() {
    this.userFirstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.userLastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.status = this.userService.getStatus() ? this.userService.getStatus() : '';

    this.patient = this.userService.getPatient();

    let state;
    this.route.params.subscribe(params => {
      state = params['state'];
      if (state == 'new') {
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.prescription = this.userService.getPrescription();
        this.prescription.posologies = this.posologies;
      }
    });
  }

  onSubmit(form) {
    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addprescription/' + this.userService.getIdMedicalFolder(), {
      startDate: form.startDate,
      endDate : form.endDate,
      prescriptionDate : dateFormatted2(),
      treatment : form.treatment,
      posology: form.posology,
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
    this.router.navigate(['doclist', { type: 'Prescription' }]);
  }
}
