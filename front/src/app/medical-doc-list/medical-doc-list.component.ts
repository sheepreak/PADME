import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "../user.service";
import {MedicalDocService} from "./medical-doc.service";

@Component({
  selector: 'app-medical-doc-list',
  templateUrl: './medical-doc-list.component.html',
  styleUrls: ['./medical-doc-list.component.css']
})
export class MedicalDocListComponent implements OnInit {
  type: string;
  listDoc: any;

  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient, private userService: UserService, private medicalDocService: MedicalDocService) {
    /*this.listDoc = {
      "id": 152,
      "exams": [{
        "id": 154,
        "motive": "Chute d'une échelle",
        "description": "Radio crânienne",
        "imgPath": null,
        "observation": "Traumatisme crânien",
        "date": "2018-02-16",
        "staffId": 23
      }],
      "prescriptions": [{
        "id": 155,
        "treatment": "Morphine",
        "posology": "Injection par intra-veineuse, 1mL toutes les 10 minutes",
        "startDate": "2018-02-16",
        "endDate": "2018-02-19",
        "prescriptionDate": "2018-03-08T11:05:35.517",
        "staffId": 23,
        "posologys": []
      }],
      "observations": [{
        "id": 153,
        "staffId": 23,
        "comment": "Le patient a été admis pour une chute violente depuis une échelle.",
        "date": "2018-03-08T11:05:35.513"
      }],
      "status": true,
      "patient": null,
      "node": 20
    }*/
  }

  ngOnInit() {
    //this.userService.setIdMedicalFolder(this.listDoc.id);

    //console.log(this.userService.getIdMedicalFolder());


    this.medicalDocService.getDocuments(152).then(data => {
      console.log("---------------");
      console.log(data);
      this.listDoc = data;
    });


    this.route.params.subscribe(params => {
      this.type = params['type'];
    });

  }

  onClicOnConsultation(consultation) {
    this.userService.setConsultation(consultation);
    this.router.navigate(['/consultationfile']);
  }

  onClicOnExamen(examen) {
    this.userService.setExamen(examen);
    this.router.navigate(['/examenfile']);
  }

  onClicOnPrescription(prescription) {
    this.userService.setPrescription(prescription);
    this.router.navigate(['/prescriptionfile']);
  }
}
