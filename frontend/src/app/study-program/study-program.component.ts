import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StudyProgram } from './study-program.model';
import { StudyProgramService } from './study-program.service';
import { YearOfStudy } from '../year-of-study/year-of-study.model';
import { Topic } from '../topic/topic.model';
import { YearOfStudyService } from '../year-of-study/year-of-study.service';
import { Subject } from '../subject/subject.model';
import { SubjectService } from '../subject/subject.service';
import { TeachingMaterialService } from '../teacher/teaching-material.service';
import { TeachingMaterial } from '../teacher/teaching-material.model';
import { FileService } from '../file/file.service';
import { File } from '../file/file.model';
import { saveAs } from 'file-saver';
import { NestedTreeControl } from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';
/*
interface FoodNode {
  name: string;
  children?: any[];
}
*/
//const TREE_DATA: FoodNode[] = [];
const TREE_DATA: any[] = [];

const MIME_TYPES = {
  pdf  : 'application/pdf',
  xls  : 'application/vnd.ms-excel',
  xlsx : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
};

@Component({
  selector: 'app-study-program',
  templateUrl: './study-program.component.html',
  styleUrls: ['./study-program.component.css']
})
export class StudyProgramComponent implements OnInit {
  //treeControl = new NestedTreeControl<FoodNode>(node => node.children);
  //dataSource = new MatTreeNestedDataSource<FoodNode>();
  treeControl = new NestedTreeControl<any>(node => node.children);
  dataSource = new MatTreeNestedDataSource<any>();

  panelOpenState = false;
  public studyProgram: StudyProgram;
  public teachingMaterials: TeachingMaterial[] = [];
  public files: File[] = [];
  public fileUrl: String = this.fileService.fileUrl;

  constructor(private studyProgramService: StudyProgramService, private fileService: FileService, private yearOfStudyService: YearOfStudyService, private subjectService: SubjectService, private teachingMaterialService: TeachingMaterialService, private route: ActivatedRoute) {
    this.dataSource.data = TREE_DATA;
  }

  ngOnInit() {
    this.route.params.subscribe(routeParams => {
      this.studyProgramService.getOne(routeParams.id).subscribe((data: StudyProgram)=>{
        this.studyProgram=data;
        this.studyProgramService.getYearsOfStudy(routeParams.id).subscribe((data: YearOfStudy[])=>{
          this.studyProgram.yearsOfStudy=data;
          for(let y of this.studyProgram.yearsOfStudy){
            this.yearOfStudyService.getSubjects(y.id).subscribe((data: Subject[])=>{
              y.subjects=data;
              for(let s of y.subjects){
                this.subjectService.getSyllabuses(s.id).subscribe((data: Topic[])=>{
                  s.syllabus=data;
                });
              }
            });
          }
        });
      });
    });
  }

  //hasChild = (_: number, node: FoodNode) => !!node.children && node.children.length > 0;
  hasChild = (_: number, node: any) => !!node.children && node.children.length > 0;

  childrenWhenFolderNotExists(item, folders, folder, file){
    if(item.children){
      item.children.forEach(child => {
        if(child.name == folders[folders.indexOf(folder)-1]){
          if(folders.indexOf(folder) == folders.length - 1){
            child.children.push({'name': folder, 'children': [{'url': file.url}]});
          }
          else{
            child.children.push({'name': folder, 'children': []});
          }
        }
        else{
          this.childrenWhenFolderNotExists(child, folders, folder, file);
        }
      })
    }
  }

  childrenWhenFolderExists(item, folders, folder, file){
    if(item.children){
      item.children.forEach(child => {
        if(child.name == folders[folders.indexOf(folder)]){
          child.children.push({'url': file.url});
        }
        else {
          this.childrenWhenFolderExists(child, folders, folder, file);
        }
      })
    }
  }

  getTeachingMaterials(id:number){
    this.dataSource.data = [];
    this.teachingMaterialService.getAllBySubject(id).subscribe(data => {
      this.teachingMaterials = data;
      this.fileService.getAllBySubject(id).subscribe(data => {
        this.files = data;
        let existingFolders: string[] = [];
        data.forEach(file => {
          let folders: string[] = file.url.split('/').slice(2, file.url.split('/').length-1);
          if(folders.length == 0){
            this.dataSource.data.push({'url': file.url});
          }
          folders.forEach(folder => {
            if(!existingFolders.includes(file.url.slice(0, file.url.indexOf(folder)+folder.length))){
              existingFolders.push(file.url.slice(0, file.url.indexOf(folder)+folder.length));
              if(folders.indexOf(folder) == 0 && folders.length > 1){
                this.dataSource.data.push({'name': folder, 'children': []})
              }
              else if(folders.indexOf(folder) == 0 && folders.length == 1){
                this.dataSource.data.push({'name': folder, 'children': [{'url': file.url}]})
              }
              else{
                this.dataSource.data.forEach(item => {
                  if(item.name == folders[folders.indexOf(folder)-1]){
                    if(folders.indexOf(folder) == folders.length - 1){
                      item.children.push({'name': folder, 'children': [{'url': file.url}]});
                    }
                    else{
                      item.children.push({'name': folder, 'children': []});
                    }
                    
                  }
                  else{
                    this.childrenWhenFolderNotExists(item, folders, folder, file);
                    /* if(item.children){
                      item.children.forEach(child => {
                        if(child.name == folders[folders.indexOf(folder)-1]){
                          if(folders.indexOf(folder) == folders.length - 1){
                            child.children.push({'name': folder, 'children': [{'url': file.url}]});
                          }
                          else{
                            child.children.push({'name': folder, 'children': []});
                          }
                        }
                      })
                    } */
                  }
                })
              }
            }
            else{/*
              if(folders.indexOf(folder) == 0){
                this.dataSource.data.push({'url': file.url})
              }*/
              if(folders.indexOf(folder)==folders.length-1){
                this.dataSource.data.forEach(item => {
                  if(item.name == folders[folders.indexOf(folder)]){
                    item.children.push({'url': file.url});
                  }
                  else{
                    this.childrenWhenFolderExists(item, folders, folder, file);
                    /*
                    if(item.children){
                      item.children.forEach(child => {
                        if(child.name == folders[folders.indexOf(folder)]){
                          child.children.push({'url': file.url});
                        }
                        else if (child.children){
                          child.children.forEach(child => {
                            if(child.children && child.name == folders[folders.indexOf(folder)]){
                              child.children.push({'url': file.url});
                            }
                          })
                        }
                      })
                    }*/
                  }
                })
              }
            }
          })
        })
      })
    })
  }

  downloadFile(fileUrl) {
    const extension = fileUrl.substr(fileUrl.lastIndexOf('.') + 1);
    const fileName = fileUrl.substr(fileUrl.lastIndexOf('/')+1);
    this.fileService.downloadFile({'fileUrl': fileUrl, 'fileName': fileName}).subscribe(data => {
      saveAs(new Blob([data], { type: MIME_TYPES[extension] }), fileName);
    });
  }  
}