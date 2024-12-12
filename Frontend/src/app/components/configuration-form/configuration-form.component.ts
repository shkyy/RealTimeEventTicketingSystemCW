import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { WebSocketService } from '../../services/websocket.service';
import { Config } from '../../interfaces/config';
import { LogDisplayComponent } from '../log-display/log-display.component';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-configuration-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, LogDisplayComponent, MatSnackBarModule],
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
})


export class ConfigurationFormComponent implements OnInit{
 
  form: FormGroup;
  showLogDisplay = false;
  logs: string[] = [];
  startListeningLogs = false;

  constructor(private webSocketService: WebSocketService, private snackBar: MatSnackBar) {
    this.form = new FormGroup({
      totalTickets: new FormControl<number>(0, [Validators.required, Validators.min(1)]),
      ticketReleaseRate: new FormControl<number>(0, [Validators.required, Validators.min(1)]),
      customerRetrievalRate: new FormControl<number>(0, [Validators.required, Validators.min(1)]),
      maxTicketCapacity: new FormControl<number>(0, [Validators.required, Validators.min(1)]),
    });
  }

  ngOnInit(): void {
    // Subscribe to WebSocket logs
    
  }

  submit(): void {
    if (this.form.valid) {
      const config = this.form.value as Config;
      this.webSocketService.send('/app/config', config);
      this.showLogDisplay = true; // Show the modal after submitting the configuration
    } else {
      this.snackBar.open('Invalid input. Please fix the errors.', 'Close', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        panelClass: ['error-snackbar']
      });
    }
  }

  onStartLogs(): void {
    if (!this.startListeningLogs) {
        this.startListeningLogs = true;
        this.webSocketService.listen('/topic/tickets', (log: string) => {
            this.logs.push(log);
        });
    }
    this.webSocketService.send('/app/resume', {});
}

  onPauseLogs(): void {
    this.webSocketService.send('/app/pause', {});
    console.log('Pause Logs');
  }

  onStopLogs(): void {
    this.webSocketService.send('/app/stop', {});
    this.logs = [];
    this.startListeningLogs = false;
    this.webSocketService.listen('/topic/tickets', () => {}); // Unsubscribe
    this.form.reset();
    this.showLogDisplay = false; 
}
}
