import {Component, OnInit} from '@angular/core';
import {ManageFile} from '../manageFile';
import {UserService} from '../user.service';
import {AdministrationRequestService} from './administration-request.service';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-administration-file',
  templateUrl: './administration-file.component.html',
  styleUrls: ['./administration-file.component.css', './../app.component.css']
})

export class AdministrationFileComponent implements OnInit {
  directory: any = {};
  user: any;
  isEmploye = true;
  oldDirectory: any;
  manageFile: ManageFile = new ManageFile();
  state;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private administrationRequest: AdministrationRequestService) {
    this.user = this.userService;
  }

  ngOnInit() {


    this.route.params.subscribe(params => {
      this.state = params['state'];
      if (this.state === 'new') {
        this.manageFile.state = ManageFile.State.New;
        this.directory = {};
      }
    });

    if (this.state !== 'new') {
      this.administrationRequest.getAdminFilePatient(this.userService.getPatientIdSelected()).then(data => {
        this.directory = data;
      }).catch(err => {
        console.log(err);
      });
    }


  }

  modifData() {
    this.oldDirectory = Object.assign({}, this.directory);
    this.manageFile.state = ManageFile.State.Edited;
  }

  cancelModif() {
    this.directory = this.oldDirectory;
    this.manageFile.state = ManageFile.State.Publish;
  }

  onSubmit(value) {

    if (this.state === 'new') {
      value.country = 'france';
      const body = {
        'adminFile': value
      };

      this.administrationRequest.addAdminFilePatient(body).then(data => {
        console.log('ajout réussi');
        this.router.navigate(['/patientlist']);
      }).catch(err => {
        console.log(err);
      });


    } else {
      console.log(value);
      this.administrationRequest.updateAdminFilePatient(this.userService.getPatientIdSelected(), value)
        .then(data => {
          console.log('ok');
          this.manageFile.state = ManageFile.State.Publish;
        }).catch(err => {
        console.log(err);
      });
    }
  }
}
