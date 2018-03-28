export class Constants {


  public static get URL_SERVER(): string {
    return 'http://localhost:8080/back-1.0-SNAPSHOT/rs';
  }

  public static get CONNECTION_URL(): string {
    return Constants.URL_SERVER + '/staff/connect';
  }

  public static get GET_STAFF_URL() {
    return Constants.URL_SERVER + '/staff';
  }

  public static get UPDATE_STAFF_SOCIO_URL() {
    return Constants.URL_SERVER + '/staff/updatesocio';
  }

  public static get GET_HOSPITALS_URL() {
    return Constants.URL_SERVER + '/hospital';
  }

  public static get UPDATE_NODE_STAFF_URL() {
    return Constants.URL_SERVER + '/staff/%d/node';
  }

  public static get ADD_EXAM_URL() {
    return Constants.URL_SERVER + '/patient/addexam/%d';
  }

  public static get GET_PATIENTS_BY_DOCTOR_URL() {
    return Constants.URL_SERVER + '/staff/patients/%d';
  }

  public static get GET_ALL_PATIENTS() {
    return Constants.URL_SERVER + '/patient';
  }

  public static get ADD_OBSERVATION_URL() {
    return Constants.URL_SERVER + '/patient/addobservation/%d';
  }

  public static get ADD_PRESCRIPTION_URL() {
    return Constants.URL_SERVER + '/patient/addprescription/%d';
  }

  public static get ADD_POSOLOGY_URL() {
    return Constants.URL_SERVER + '/medicalFile/%d/posology';
  }

  public static get GET_ADMIN_FILE_URL() {
    return Constants.URL_SERVER + '/patient/%d/adminfile';
  }

  public static get GET_MEDICAL_FILE_URL() {
    return Constants.URL_SERVER + '/medicalFile/%d';
  }

  public static get GET_STAFF_BY_ID_URL() {
    return Constants.URL_SERVER + '/staff/%d';
  }

  public static get ADD_IMAGE_MEDICAL_URL() {
    return Constants.URL_SERVER + '/medicalFile/image/%d';
  }

  public static get GET_IMAGE_MEDICAL_URL() {
    return Constants.URL_SERVER + '/medicalFile/image/%s';
  }


  public static get ADD_NODE_URL() {
    return Constants.URL_SERVER + '/node/%d';
  }

  public static get GET_NODE_URL() {
    return Constants.URL_SERVER + '/node';
  }
}
