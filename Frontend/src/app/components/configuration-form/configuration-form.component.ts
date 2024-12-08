import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { WebSocketService } from '../../services/websocket.service';
import { Config } from '../../interfaces/config';

@Component({
  selector: 'app-configuration-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
})


export class ConfigurationFormComponent implements OnInit {
 
  configParams: Config[] = [];

  form: FormGroup = new FormGroup({
    totalTickets: new FormControl<number>(0, Validators.required),
    maxTicketCapacity: new FormControl<number>(0, Validators.required),
    ticketReleaseRate: new FormControl<number>(0, Validators.required),
    customerRetrievalRate: new FormControl<number>(0, Validators.required),
  });

  constructor(private webSocketService: WebSocketService) {}

  ngOnInit(): void {
    this.webSocketService.listen(config => this.configParams.push(config))
  }

  add(totalTickets: number, maxTicketCapacity: number, ticketReleaseRate: number, customerRetrievalRate: number): void {
    const config: Config = {
      totalTickets: totalTickets,
      maxTicketCapacity: maxTicketCapacity,
      ticketReleaseRate: ticketReleaseRate,
      customerRetrievalRate: customerRetrievalRate
    };
    this.webSocketService.send(config);
  }

  click(): void {
    this.add(this.form.value.totalTickets, this.form.value.maxTicketCapacity, this.form.value.ticketReleaseRate, this.form.value.customerRetrievalRate);
    this.form.reset({});
  }
  
}
