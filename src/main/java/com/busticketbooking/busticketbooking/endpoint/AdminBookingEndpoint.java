package com.busticketbooking.busticketbooking.endpoint;

import com.busticketbooking.busticketbooking.models.Booking;
import com.google.gson.Gson;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/admin/bookings")
public class AdminBookingEndpoint {

    private static Set<Session> adminSessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        adminSessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        adminSessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Handle incoming messages from admins if necessary
    }

    public static void broadcastNewBooking(String bookingJson) {
        synchronized (adminSessions) {
            for (Session session : adminSessions) {
                if (session.isOpen()) {
                    try {
                        session.getBasicRemote().sendText(bookingJson);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}