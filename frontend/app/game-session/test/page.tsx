"use client";

import { useEffect, useState } from "react";
import { initializeStompClient } from "./stompClient.tsx";

export default function WebSocketComponent() {
    const [connected, setConnected] = useState<boolean>(false);
    const [messages, setMessages] = useState<string[]>([]);
    const [stompClient, setStompClient] = useState<any>(null);

    useEffect(() => {
        const setupWebSocket = async () => {
            try {
                const client = await initializeStompClient();
                if (!client) return;

                setStompClient(client);

                client.onConnect = (frame) => {
                    setConnected(true);
                    console.log("Connected:", frame);

                    client.subscribe("/topic/greetings/123", (message) => {
                        const content: string = JSON.parse(message.body).message;
                        console.log("recieved your message")
                        setMessages((prevMessages) => [...prevMessages, content]);
                    });
                };

                client.onWebSocketError = (error) => {
                    console.error("WebSocket Error:", error);
                };

                client.onStompError = (frame) => {
                    console.error("STOMP Error:", frame.headers["message"]);
                };

                client.activate();
            } catch (error) {
                console.error("Error initializing WebSocket:", error);
            }
        };

        setupWebSocket();

        return () => {
            if (stompClient) {
                stompClient.deactivate();
                setConnected(false);
            }
        };
    }, []);

    const sendMessage = () => {
        if (!stompClient) {
            console.error("STOMP client is not initialized");
            return;
        }

        stompClient.publish({
            destination: "/app/hello",
            body: JSON.stringify({ message: "Anas" }),
        });
    };

    return (
        <div>
            <h2>{connected ? "Connected to WebSocket" : "Disconnected"}</h2>
            <button onClick={sendMessage} disabled={!connected}>
                Send Message
            </button>
            <ul>
                {messages.map((msg, index) => (
                    <li key={index}>{msg}</li>
                ))}
            </ul>
        </div>
    );
}
