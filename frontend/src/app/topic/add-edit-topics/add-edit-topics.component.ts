import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SubjectRealization } from 'src/app/subject/subject-realization.model';
import { TeacherService } from 'src/app/teacher/teacher.service';
import { AuthService } from 'src/app/auth/auth.service';
import { TopicService } from '../topic.service';
import { Topic } from '../topic.model';
import { mimeTypeValidator } from 'src/app/validators/mime-type-validator.directive';
import { FormErrorService } from 'src/app/shared/formError.service';
import { ActivatedRoute } from '@angular/router';
import { SubjectRealizationService } from 'src/app/subject/subject-realization.service';
import { FileService } from 'src/app/file/file.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { MatDialog } from '@angular/material';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

@Component({
  selector: 'app-add-edit-topics',
  templateUrl: './add-edit-topics.component.html',
  styleUrls: ['./add-edit-topics.component.css']
})
export class AddEditTopicsComponent implements OnInit {

  public addEditTopicsForm : FormGroup;
  public subjectRealizations: SubjectRealization[] = [];
  private subjectRealization: SubjectRealization;
  public weeks: Week[] = [];
  public iconPreview: string;
  private id : String;
  public fileUrl: String = this.fileService.fileUrl;

  constructor(private fb: FormBuilder, private route: ActivatedRoute,
    private teacherService: TeacherService, private topicService: TopicService,
    private authService: AuthService, private subjectRealizationService: SubjectRealizationService,
    private fileService: FileService, public formError: FormErrorService, public dialog: MatDialog,
    private snackBarService: SnackBarService) {
      
    }

  ngOnInit() {
    this.addEditTopicsForm = this.fb.group({
      subjectRealization: ['', {validators: [Validators.required]}],
      topic: ['', {validators: []}],
      week: ['', {validators: [Validators.required]}],
      icon: ['', {asyncValidators: [mimeTypeValidator]}],
    });

    if(this.route.snapshot.paramMap.get("id")){
      this.id = this.route.snapshot.paramMap.get("id");
      this.subjectRealizationService.getOne(this.id).subscribe(data => {
        this.subjectRealization = data;
        this.addEditTopicsForm.patchValue({subjectRealization: data});
        this.subjectRealizations.push(this.subjectRealization);
        this.topicService.getAllBySubjectId(this.subjectRealization.subject.id).subscribe((data: Topic[]) => {
          let tempTopics: Topic[] = data;
          tempTopics.sort((t1, t2) => (t1.week > t2.week) ? 1 : -1);
          tempTopics.forEach(t => {
            let week = new Week(t.week);
            let exists = false;
            this.weeks.forEach(week => {
              if(t.week == week.weekNumber){
                exists = true;
              }
            })
            if(!exists){
              this.weeks.push(week);
            }
            
            for(let i = 0; i < t.week; i++){
              if(this.weeks[i].weekNumber != i+1){
                this.weeks.splice(i, 0, new Week(i+1));
              }
            }
            this.weeks[t.week-1].topics.push([t, null, null]);
          });
        });
      });
    }
    else{
      this.getSubjectRealizations();
    }
  }

  save(){
    this.weeks.forEach(week => {
      week.topics.forEach(topic => {
        if(topic[0].id){
          this.topicService.update(topic[0].id, topic[0]).subscribe();
        }
        else{
          this.topicService.add(topic[0], topic[1]).subscribe(_ => {
            this.addEditTopicsForm.reset();
          });
        }
      })
    });
    this.snackBarService.openSnackBar("Successfully saved topics", "X");
  }

  getSubjectRealizations(){
    let loggedUser = this.authService.getCurrentUser();
    this.teacherService.getSubjectRealization(loggedUser).subscribe(data => {
      this.subjectRealizations = data;
    });
  }

  addTopic(){
    this.addEditTopicsForm.get('week').value.topics.push([new Topic(this.addEditTopicsForm.get('topic').value, this.addEditTopicsForm.get('week').value.weekNumber, this.addEditTopicsForm.get('subjectRealization').value.subject, null), this.addEditTopicsForm.get('icon').value, this.iconPreview]);
    delete this.iconPreview;
    this.addEditTopicsForm.patchValue({ icon: "" });
    this.addEditTopicsForm.get("icon").updateValueAndValidity();
    this.addEditTopicsForm.patchValue({ topic: "" });
    this.addEditTopicsForm.get("topic").updateValueAndValidity();
    this.snackBarService.openSnackBar("Successfully added new topic", "X")
  }

  addWeek(){
    this.weeks.push(new Week(this.weeks.length+1));
    this.snackBarService.openSnackBar("Successfully added new week", "X")
  }

  onImagePicked(event: Event) {
    const file = (event.target as HTMLInputElement).files[0];
    if(file){
      this.addEditTopicsForm.patchValue({ icon: file });
      this.addEditTopicsForm.get("icon").updateValueAndValidity();
      const reader = new FileReader();
      reader.onload = () => {
        this.iconPreview = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  deleteTopic(week:Week, topic: [Topic, File, string]){
    if(topic[0].id){
      this.topicService.delete(topic[0].id).subscribe(_ => {
        this.weeks[this.weeks.indexOf(week)].topics.splice(this.weeks[this.weeks.indexOf(week)].topics.indexOf(topic), 1);
      });
    }
    else{
      this.weeks[this.weeks.indexOf(week)].topics.splice(this.weeks[this.weeks.indexOf(week)].topics.indexOf(topic), 1);
    }
    this.snackBarService.openSnackBar("Successfully deleted topic", "X")
  }

  openDialog(week:Week, topic: [Topic, File, string]): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete topic", content: "Are you sure you want to delete this topic?"}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.deleteTopic(week, topic);
      };
    });
  }
  
}

class Week{
  weekNumber:number;
  topics: [Topic, File, string][];

  constructor(weekNumber:number){
    this.weekNumber = weekNumber;
    this.topics = [];
  }
}
