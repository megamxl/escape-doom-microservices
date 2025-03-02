import { Client } from "@stomp/stompjs";

export const initializeStompClient = async () => {
    try {
        const client = new Client({
            brokerURL: "ws://localhost:8081/player-api/gs-guide-websocket",
            reconnectDelay: 5000, // Auto-reconnect after 5 sec
        });

        return client;
    } catch (error) {
        console.error("Error initializing STOMP client:", error);
        return null;
    }
};
