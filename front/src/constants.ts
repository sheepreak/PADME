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
}
