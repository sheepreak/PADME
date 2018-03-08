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
  directory = {
    treatment: '',
    startDate: '',
    endDate: '',
    prescriptionDate: '',
    posologie: ''
  }

  posologies = [
    {date: "11h",
    observation: "Le patient va bien",
    Infirmier: "Ameline MOREAU"},
    {date: "13h",
      observation: "Le patient va toujour bien",
      Infirmier: "Ameline MOREAU"}
  ]

  oldDirectory;
  manageFile: ManageFile = new ManageFile();
  firstName: string;
  lastName: string;
  status: string;

  patientFirstName: string;
  patientLastName: string;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private http: HttpClient) {
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
        this.userService.resetPrescription();
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.directory.treatment = this.userService.getPrescription().treatment;
        this.directory.startDate = this.userService.getPrescription().startDate;
        this.directory.endDate = this.userService.getPrescription().endDate;
        this.directory.prescriptionDate = this.userService.getPrescription().prescriptionDate;
        this.directory.posologie = this.userService.getPrescription().posology;
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

    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addprescription/72', {
      startDate: form.startDate,
      endDate : form.endDate,
      prescriptionDate : date,
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
