

function dateFormatted1 (): string
{
  var today = new Date();
  var jj = today.getDay().toString();
  var mm = (today.getMonth() + 1).toString();
  var aaaa = today.getFullYear();

  if (jj.length != 2) {
    jj = "0".concat(jj);
  }
  if (mm.length != 2) {
    mm = "0".concat(mm);
  }

  return jj+"/"+mm+"/"+aaaa;
}

function dateFormatted2 (): string {
  var today = new Date();
  var jj = today.getDay().toString();
  var mm = (today.getMonth() + 1).toString();
  var aaaa = today.getFullYear();

  if (jj.length != 2) {
    jj = "0".concat(jj);
  }
  if (mm.length != 2) {
    mm = "0".concat(mm);
  }

  return aaaa + "-" + mm + "-" + jj;
}
