import { Client } from "@stomp/stompjs";

export const initializeStompClient = async () => {
    try {
        const client = new Client({
            brokerURL: `${process.env.NEXT_PUBLIC_GW_WS}/player-api/player-join`,
            reconnectDelay: 5000, // Auto-reconnect after 5 sec
        });

        return client;
    } catch (error) {
        console.error("Error initializing STOMP client:", error);
        return null;
    }
};
