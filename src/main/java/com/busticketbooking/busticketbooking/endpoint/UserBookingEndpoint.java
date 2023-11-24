package com.busticketbooking.busticketbooking.endpoint;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/booking/{bookingId}")
public class UserBookingEndpoint {

    private static ConcurrentHashMap<String, Session> userSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("bookingId") String bookingId, Session session) {
        userSessions.put(bookingId, session);
    }

    @OnClose
    public void onClose(@PathParam("bookingId") String bookingId, Session session) {
        userSessions.remove(bookingId);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Handle incoming messages from users if necessary
    }

    public static void sendBookingUpdate(String bookingId, String message) {
        Session session = userSessions.get(bookingId);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
