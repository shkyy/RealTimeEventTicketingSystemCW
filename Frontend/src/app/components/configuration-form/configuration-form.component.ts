import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { WebSocketService } from '../../services/websocket.service';
import { Config } from '../../interfaces/config';
import { LogDisplayComponent } from '../log-display/log-display.component';

@Component({
  selector: 'app-configuration-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, LogDisplayComponent],
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
})


export class ConfigurationFormComponent implements OnInit {
 
  form: FormGroup = new FormGroup({
    totalTickets: new FormControl<number>(0, Validators.required),
    maxTicketCapacity: new FormControl<number>(0, Validators.required),
    ticketReleaseRate: new FormControl<number>(0, Validators.required),
    customerRetrievalRate: new FormControl<number>(0, Validators.required),
  });

  constructor(private webSocketService: WebSocketService) {}

  ngOnInit(): void {
    this.webSocketService.listen('/topic/tickets', (message) => console.log('Acknowledgement or log: ', message))
  }

  submit(): void {
    const config: Config = this.form.value;
    this.webSocketService.send('/app/config', config);
    this.form.reset();
  }
  
}
