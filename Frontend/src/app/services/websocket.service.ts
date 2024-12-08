import { Injectable, OnDestroy } from '@angular/core';
import * as StompJS from '@stomp/stompjs';
import { Config } from '../interfaces/config';

export type ListnerCallBack = (message: string) => void; 

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

  public send<T>(destination: string, payload: T): void {
    if(this.connection && this.connection.connected) {
      this.connection.send(destination, {}, JSON.stringify(payload));
    }
  }

  public listen(destination: string, callback: ListnerCallBack): void {
    if(this.connection) {
      this.connection.connect({}, () => {
        this.subscription = this.connection!.subscribe(destination, (message) => callback(message.body));
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
