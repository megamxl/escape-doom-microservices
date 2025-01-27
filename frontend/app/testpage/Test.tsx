'use client'

import { useState, useEffect } from "react";

export default function Test() {

    const [socket, setSocket] = useState<WebSocket | null>(null);
    const [inputValue, setInputValue] = useState<string>("");
    const [messages, setMessages] = useState<string[]>([]);


    useEffect(() => {
        const ws = new WebSocket("ws://localhost:8090/ws/started");

        ws.onopen = () => {
            console.log("WebSocket connection established");
        };

        ws.onmessage = (event) => {
            setMessages((prevMessages) => [...prevMessages, `Server: ${event.data}`]);
        };

        ws.onerror = (error) => {
            console.error("WebSocket error:", error);
        };

        ws.onclose = () => {
            console.log("WebSocket connection closed");
        };

        setSocket(ws);

        return () => {
            ws.close();
        };
    }, []);

    const handleSendMessage = () => {
        if (socket && inputValue.trim()) {
            socket.send(inputValue);
            setMessages((prevMessages) => [...prevMessages, `Client: ${inputValue}`]);
            setInputValue("");
        }
    };

    return (
        <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
            <h1>WebSocket-Test</h1>
            <div
                style={{
                    border: "1px solid #ccc",
                    padding: "10px",
                    marginBottom: "10px",
                    height: "200px",
                    overflowY: "auto",
                    color: "#000000",
                    backgroundColor: "#f9f9f9",
                }}
            >
                {messages.length === 0 ? (
                    <p>No nix do</p>
                ) : (
                    messages.map((msg, index) => <p key={index}>{msg}</p>)
                )}
            </div>
            <div style={{ display: "flex", gap: "10px" }}>
                <input
                    type="text"
                    value={inputValue}
                    onChange={(e) => setInputValue(e.target.value)}
                    style={{
                        flex: 1,
                        padding: "10px",
                        border: "1px solid #ccc",
                        color: "#000000",
                        borderRadius: "4px",
                    }}
                    placeholder="..."
                />
                <button
                    onClick={handleSendMessage}
                    style={{
                        padding: "10px 20px",
                        backgroundColor: "#0070f3",
                        color: "#fff",
                        border: "none",
                        borderRadius: "4px",
                        cursor: "pointer",
                    }}
                >
                    Send
                </button>
            </div>
        </div>
    );
}
