import {ViewChild} from "@angular/core";
import {logger} from "codelyzer/util/logger";
import {log} from "util";

export class Image {
  name: string;
  img: string;
  miniature: string;
  mini = true;
  currentpath: string;

  constructor(name: string, path: string) {
    this.name = name;
    this.img = path;
    this.miniature = "../../assets/img/icon/add-image.png";
    this.currentpath = this.miniature;
  }

  changeImg(){
    if (this.mini){
      this.loadImg();
    }else{
      this.unloadImg();
    }
  }

  loadImg(){
    this.currentpath = this.img;
    this.mini = false;
  }

  unloadImg(){
    this.currentpath = this.miniature;
    this.mini = true;
  }
}
