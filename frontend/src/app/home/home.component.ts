import { Component, OnInit } from '@angular/core';
import { FileService } from '../file/file.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public fileUrl: string = this.fileService.fileUrl;
  public homeImageUrl: string = "images/home/welcome.jpg";
  
  constructor(private fileService: FileService) { }

  ngOnInit() {
  }

}
