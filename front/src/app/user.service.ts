import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {WebApiPromiseService} from './web-api-promise.service';


@Injectable()
export class UserService {

  private connected: boolean;
  private login: string;
  private password: string;
  private token: string;
  private firstName: string;
  private lastName: string;
  private status: string;
  private phone: string;
  private address: string;
  private id: string;

  marginTop = 0;
  marginLeft = 0;
  marginBody = 0;

  private patient = {
    'patientId': null,
    'firstName': null,
    'lastName': null,
    'genre': null,
    'country': null,
    'birthDate': null,
    'idMedicalFile': null
  };

  private consultation = {
    'id': null,
    'staffId': null,
    'comment': null,
    'date': null
  }

  private examen = {
    'id': null,
    'motive': null,
    'description': null,
    'observation': null,
    'imgPath': null,
    'date': null,
    'staffId': null
  }

  private prescription = {
    'id':null,
    'startDate': null,
    'endDate': null,
    'date': null,
    'treatment': null,
    'posologies': []
  }

  private idMedicalFolder: null;

  constructor(private requester: WebApiPromiseService, private router: Router) {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user != null) {
      this.requester.connectUser(user.login, user.password).then(data => {
        this.connect(user.login, user.password, data);
      }).catch(err => {
        localStorage.clear();
        this.router.navigate(['/']);
        console.log(err);
      });
    }
  }

  connect(login, password, user) {
    this.connected = true;
    this.login = login;
    this.password = password;
    this.token = user.token;
    this.firstName = user.firstName;
    this.lastName = user.lastName;
    this.status = user.status.toUpperCase();
    this.phone = user.phone;
    this.address = user.address;
    this.id = user.id;

    localStorage.setItem('user', JSON.stringify(
      {
        'id': user.id,
        'login': login,
        'password': this.password,
        'token': user.token,
        'firstName': user.firstName,
        'lastName': user.lastName,
        'status': user.status.toUpperCase(),
        'address': user.address,
        'phone': user.phone
      }));
  }

  isConnected() {
    return this.connected;
  }

  loggout() {
    this.login = null;
    this.token = null;
    this.firstName = null;
    this.lastName = null;
    this.connected = false;
    localStorage.clear();
    this.setPatient(null);
  }

  getToken() {
    return this.token;
  }

  getLogin() {
    return this.login;
  }

  getfirstName(): string {
    return this.firstName;
  }

  getlastName(): string {
    return this.lastName;
  }

  getStatus() {
    return this.status;
  }

  getPhone() {
    return this.phone;
  }

  setPatient(patient) {
    this.patient = patient;
    console.log(patient);
  }

  setConsultation(consultation) {
    this.consultation = consultation;
  }

  setExamen(examen) {
    this.examen = examen;
  }

  setPrescription(prescription) {
    this.prescription = prescription;
  }

 setIdMedicalFolder(id){
    this.idMedicalFolder = id;
 }

 getIdMedicalFolder(){
    return this.patient.idMedicalFile;
 }

  getPatientIdSelected() {
    if (this.patient != null) {
      return this.patient.patientId;
    }
    return null;
  }

  getPatient() {
    return this.patient;
  }

  getConsultation(){
    return this.consultation;
  }

  getExamen(){
    return this.examen;
  }

  getPrescription(){
    return this.prescription;
  }

  getAdress() {
    return this.address;
  }

  isAdmin() {
    return this.status === 'ADMIN';
  }

  isDoctor() {
    return this.status === 'DOCTOR';
  }

  isNurse() {
    return this.status === 'NURSE';
  }

  isSecretary() {
    return this.status === 'SECRETARY';
  }

  getId() {
    return this.id;
  }


}
