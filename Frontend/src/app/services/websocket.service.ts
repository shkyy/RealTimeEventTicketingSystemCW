import { Injectable, OnDestroy } from '@angular/core';
import * as StompJS from '@stomp/stompjs';
import { Config } from '../interfaces/config';

export type ListnerCallBack = (message: Config) => void; 

@Injectable({
  providedIn: 'root'
})


export class WebSocketService implements OnDestroy{

  private connection: StompJS.CompatClient | undefined = undefined;

  private subscription: StompJS.StompSubscription | undefined;

  constructor() {
    this.connection = StompJS.Stomp.client('ws://localhost:8080/ws');
    this.connection.connect({}, () => {});
  }

  public send(config: Config): void {
    if(this.connection && this.connection.connected) {
      this.connection.send('/app/config', {}, JSON.stringify(config));
    }
  }

  public listen(fun: ListnerCallBack): void {
    if(this.connection) {
      this.connection.connect({}, () => {
        this.subscription = this.connection!.subscribe('/topic/tickets', message => fun(JSON.parse(message.body)));
      })
    }
  }

  // Unsubscribe to clean up the process
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
  
}
