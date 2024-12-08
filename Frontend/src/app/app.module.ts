import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from '@angular/forms';

import { AppComponent } from "./app.component";
import { ConfigurationFormComponent } from "./components/configuration-form/configuration-form.component";


@NgModule({
    declarations: [AppComponent, ConfigurationFormComponent],
    imports: [BrowserModule, FormsModule],
    providers: [ConfigurationFormComponent],
    bootstrap: [AppComponent],
})

export class AppModule {}