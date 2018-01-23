import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-static-nav-bar',
  templateUrl: './static-nav-bar.component.html',
  styleUrls: ['./../../bootstrap/css/bootstrap.css', './static-nav-bar.component.css']
})
export class StaticNavBarComponent implements OnInit {
  results: string[];


  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http
      .get<ItemsResponse>('/api/items')
      .subscribe(
        data => {
          this.results = data.results;
          },
        (err: HttpErrorResponse) => {
          if (err.error instanceof Error) {
            console.log('An error occurred:', err.error.message);
          } else {
            console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
          }
        }
      );
  }

}
