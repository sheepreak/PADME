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
    {date: Date.now().toString(),
    observation: "Le patient va bien",
    infirmier: "Ameline MOREAU",
    isTake:true},
    {date: Date.now().toString(),
      observation: "Le patient ne veux pas prendre sont traitement",
      infirmier: "Ameline MOREAU",
      isTake:false}
  ]

  manageFile: ManageFile = new ManageFile();
  userFirstName: string;
  userLastName: string;
  status: string;
  patient: any;
  user: any;
  addPosology: false;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private http: HttpClient) {
    this.user = this.userService;
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

  onAddPosology(form){
    /*
    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addprescription/' + this.userService.getIdMedicalFolder(), {
      date: dateFormatted2(),
      observation : form.observation,
      infirmier: this.user.getfirstName() + this.userLastName,
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

    */

    this.posologies.push({
      date: new Date().toDateString(),
      observation : form.observation,
      infirmier: this.user.getfirstName() + this.userLastName,
      isTake:form.isTake
    });

    this.addPosology=false;
  }
}
