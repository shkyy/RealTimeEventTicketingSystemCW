import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ControlPanelComponent } from '../control-panel/control-panel.component';

@Component({
    selector: 'app-log-display',
    standalone: true,
    imports: [CommonModule, ControlPanelComponent],
    templateUrl: './log-display.component.html',
    styleUrl: './log-display.component.css'
})


export class LogDisplayComponent {
  @Input() logs: string[] = [];
  @Input() showLogs = false;

  @Output() start = new EventEmitter<void>();
  @Output() pause = new EventEmitter<void>();
  @Output() stop = new EventEmitter<void>();

  startLogs(): void {
    this.start.emit();
  }

  pauseLogs(): void {
    this.pause.emit();
  }

  stopLogs(): void {
    this.stop.emit();
  }
}

