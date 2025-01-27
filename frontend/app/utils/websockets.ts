type webSocketParams = {
    url: string,
    onMessage: (event?: MessageEvent) => void
}

export const createWebSocket = ({url, onMessage}: webSocketParams): WebSocket => {
    const ws = new WebSocket(url);

    ws.onopen = () => {
        console.log(`WebSocket connection to ${url} established`);
    }

    ws.onmessage = (event: MessageEvent) => {
        onMessage(event);
    }

    ws.onerror = (error: Event) => {
        console.error(`WebSocket error for ${url}:`, error);
    }

    ws.onclose = () => {
        console.log(`WebSocket connection to ${url} closed`);
    };

    return ws;
}
