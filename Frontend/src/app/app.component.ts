import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';
@Component({
    selector: 'app-root',
    standalone: true,
    imports: [
        RouterOutlet,
        CommonModule,
        ConfigurationFormComponent
    ],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
})

export class AppComponent {
  title = 'Frontend';
}
