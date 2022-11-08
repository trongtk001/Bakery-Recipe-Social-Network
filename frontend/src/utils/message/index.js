
import SockJS from 'sockjs-client';
import * as StompJs from '@stomp/stompjs';


export const connect = (header) => {
    const client = new StompJs.Client({
        brokerURL: "ws://35.198.245.224/api/chat",
        connectHeaders: header,
        debug: function (str) {
            console.log(str);
        },
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });


    if (typeof WebSocket !== 'function') {
        client.webSocketFactory = function () {
            return new SockJS(`http://35.198.245.224/api/chat`);
        };
    }
    client.activate();
    return client;
}

export const disconect = (stompClient) => {
    stompClient.deactivate();
}

export const subscribeOnline = (header, callback, stompClient) => {
    stompClient.subscribe("/app/chat/online", callback, header)
}

export const subscribePrivateChat = (header, username, callback, stompClient) => {
    stompClient.subscribe(`/user/${username}/queue/private`, callback, header)
}

export const subscribeError = (header, username, callback, stompClient) => {
    stompClient.subscribe(`/user/${username}/queue/private`, callback, header)
}

export const joinChat = (header, stompClient) => {
    stompClient.publish({
        destination: "/app/chat/join",
        header
    });
}

export const sendMess = (header, message, stompClient) => {
    stompClient.publish({
        destination: "/app/private-message",
        body: JSON.stringify(message),
        headers: header,
        isBinaryBody: false
    });
}




