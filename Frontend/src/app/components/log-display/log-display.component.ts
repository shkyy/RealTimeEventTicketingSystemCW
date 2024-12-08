import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../../services/websocket.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-log-display',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './log-display.component.html',
    styleUrl: './log-display.component.css'
})
export class LogDisplayComponent implements OnInit{

  logs: string[] = [];

  constructor(private webSocketService: WebSocketService) {}


  ngOnInit(): void {
    this.webSocketService.listen('/topic/tickets', (logMessage) => this.logs.push(logMessage));
  }



}
