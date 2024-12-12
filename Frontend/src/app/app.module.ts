import { NgModule } from "@angular/core";
import { FormsModule } from '@angular/forms';

import { AppComponent } from "./app.component";
import { ConfigurationFormComponent } from "./components/configuration-form/configuration-form.component";
import { CommonModule } from "@angular/common";


@NgModule({
    declarations: [AppComponent],
    imports: [CommonModule, FormsModule],
    providers: [ConfigurationFormComponent],
    bootstrap: [AppComponent],
})

export class AppModule {}