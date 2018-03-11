import {Component, OnInit} from '@angular/core';
import {ManageFile} from "../manageFile";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {HttpClient} from "@angular/common/http";
import {MedicalDocService} from "../medical-doc-list/medical-doc.service";
import {MedicalFileService} from "../medical-file.service";

@Component({
  selector: 'app-prescription-file',
  templateUrl: './prescription-file.component.html',
  styleUrls: ['./prescription-file.component.css', './../app.component.css']
})
export class PrescriptionFileComponent implements OnInit {
  prescription: any;
  manageFile: ManageFile = new ManageFile();
  userFirstName: string;
  userLastName: string;
  status: string;
  patient: any;
  user: any;
  addPosology: false;
  doctor: any;

  constructor(private medicalService: MedicalFileService, private router: Router, private route: ActivatedRoute, private userService: UserService, private http: HttpClient) {
    this.user = this.userService;
  }

  ngOnInit() {
    this.userFirstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.userLastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.status = this.userService.getStatus() ? this.userService.getStatus() : '';
    this.patient = this.userService.getPatient();

    console.log(this.patient);

    let state;
    this.route.params.subscribe(params => {
      state = params['state'];
      if (state == 'new') {
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.prescription = this.userService.getPrescription();
      }
    });

    if (this.manageFile.statePublish()) {
      this.medicalService.getDoctor(this.prescription.staffId).then(data => {
        this.doctor = data;
      });
    }
  }

  onSubmit(form) {
    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient/addprescription/' + this.userService.getIdMedicalFolder(), {
      startDate: form.startDate,
      endDate : form.endDate,
      date : Date.now().toString(),
      treatment : form.treatment,
      posology: [],
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

  onAddPosology(form) {
    const req = this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/medicalFile/' + this.prescription.id + '/posology', {
      date: Date.now().toString(),
      observation: form.observation,
      nurseName: this.userFirstName,
      nurseSurname: this.userLastName,
      taken: form.taken
    })
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log("Error occured");
        }
      );
    this.addPosology = false;
  }
}
