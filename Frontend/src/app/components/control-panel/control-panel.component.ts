import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
    selector: 'app-control-panel',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './control-panel.component.html',
    styleUrl: './control-panel.component.css'
})
export class ControlPanelComponent {
  @Output() start = new EventEmitter<void>();
  @Output() pause = new EventEmitter<void>();
  @Output() stop = new EventEmitter<void>();

  isRunning = false;
  isPaused = false;

  startLogs(): void {
    if (!this.isRunning) {
      this.isRunning = true;
      this.isPaused = false;
      this.start.emit(); 
  }
  }

  pauseOrResumeLogs(): void {
    if (this.isRunning) {
      if (this.isPaused) {
          this.isPaused = false;
          this.start.emit(); 
      } else {
          this.isPaused = true;
          this.pause.emit(); 
      }
    }
  }

  stopLogs(): void {
    if (this.isRunning) {
      this.isRunning = false;
      this.isPaused = false;
      this.stop.emit();
  }
  }
}
