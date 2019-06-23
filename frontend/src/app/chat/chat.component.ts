import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs'
import { Message } from './message.model';
import { AuthService } from '../auth/auth.service';
import { MessageService } from './message.service';
import { FileService } from '../file/file.service';
import { TeacherService } from '../teacher/teacher.service';
import { StudentService } from '../student/student.service';
import { Teacher } from '../teacher/teacher.model';
import { Student } from '../student/student.model';
import {FormControl} from '@angular/forms';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  public fileUrl: string = this.fileService.fileUrl;
  private wc;
  public messages: Message[] = [];
  public groupedMessages: string[] = [];
  public conversation: Message[] = [];
  public msg: string;
  private message:Message;
  public loggedUser: string = this.authService.getCurrentUser();
  public recipient: string = '';
  public teachers: Teacher[] = [];
  public students: Student[] = [];
  public newMessage = false;

  myControl = new FormControl();
  options: string[] = [];
  filteredOptions: string[] = [];

  displayedColumns = ['recipient'];
  dataSource = new MatTableDataSource<Message>(this.conversation);

  constructor(private authService : AuthService, private messageService: MessageService, private fileService : FileService, private studentService: StudentService, private teacherService: TeacherService) {
    this.wc = Stomp.client("ws://localhost:8080/ws");
    this.wc.connect({}, ()=>{
      this.wc.subscribe("/topic/ws", (msg)=>{
        this.messages.push(JSON.parse(msg.body));
        if(JSON.parse(msg.body).recipient == this.authService.getCurrentUser() || JSON.parse(msg.body).sender == this.authService.getCurrentUser()){
          this.conversation.push(JSON.parse(msg.body));
          this.dataSource.data = this.conversation;
          if(document.getElementsByName('bottom').length > 1){
            document.getElementsByName('bottom').item(document.getElementsByName('bottom').length-1).scrollIntoView(true);
          }
        }
      })
    })
  }

  ngOnInit() {
    this.getAllConversations();
    this.getAllTeachers();
    this.getAllStudents();
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  onInputChanged(searchStr: string): void {
    this.filteredOptions = this._filter(searchStr);
    }

  send(){
    if(this.msg && this.msg.trim() != ''){
      this.message = new Message(this.msg, new Date(), this.recipient, this.authService.getCurrentUser(), []);
      this.wc.send("/app/ws", {}, JSON.stringify(this.message));
      this.msg = '';
    }
  }

  showConversation(person: string){
    this.recipient = person;
    this.messages.forEach(message => {
      if(message.recipient == person || message.sender == person){
        this.conversation.push(message);
        this.dataSource.data = this.conversation;
      }
    })
  }

  getAllConversations(){
    this.messageService.getAllByUser(this.authService.getCurrentUser()).subscribe(data => {
      this.messages = data;
      data.forEach(message => {
        if(message.recipient != this.authService.getCurrentUser() && !this.groupedMessages.includes(message.recipient)){
          this.groupedMessages.push(message.recipient);
        }
        if(message.sender != this.authService.getCurrentUser() && !this.groupedMessages.includes(message.sender)){
          this.groupedMessages.push(message.sender);
        }
      })
    });
  }

  getAllStudents(){
    this.studentService.getAll().subscribe(data => {
      this.students = data;
      data.forEach(student => {
        this.options.push(student.accountData.username);
        this.filteredOptions.push(student.accountData.username);
      })
    })
  }

  getAllTeachers(){
    this.teacherService.getAll().subscribe(data => {
      this.teachers = data;
      data.forEach(teacher => {
        this.options.push(teacher.accountData.username);
        this.filteredOptions.push(teacher.accountData.username);
      })
    })
  }
}
