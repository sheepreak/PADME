import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-static-nav-bar',
  templateUrl: './static-nav-bar.component.html',
  styleUrls: ['./static-nav-bar.component.css', '../../assets/bootstrap3/dist/css/bootstrap.css']
})
export class StaticNavBarComponent implements OnInit {
  results: string[];


  constructor(private http: HttpClient) { }

  ngOnInit(): void {

  }

}
