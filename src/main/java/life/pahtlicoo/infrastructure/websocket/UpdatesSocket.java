package life.pahtlicoo.infrastructure.websocket;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.json.bind.JsonbBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/updates/{username}")
@ApplicationScoped
public class UpdatesSocket {

    // username → single session (only one tab/device allowed)
    private static final Map<Integer, Session> userSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String usernameStr) {
        try {
            Integer username = Integer.valueOf(usernameStr);

            // If there's already a session for this user, close it
            Session existing = userSessions.get(username);
            if (existing != null && existing.isOpen()) {
                try {
                    existing.close();
                    System.out.println("Closed existing session for user: " + username);
                } catch (Exception e) {
                    System.err.println("Failed to close existing session: " + e.getMessage());
                }
            }

            userSessions.put(username, session);
            System.out.println("WebSocket opened for user: " + username);

        } catch (NumberFormatException e) {
            session.getAsyncRemote().sendText("{\"error\": \"Invalid username format\"}");
            try {
                session.close();
            } catch (Exception ignored) {}
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String usernameStr) {
        try {
            Integer username = Integer.valueOf(usernameStr);
            Session existing = userSessions.get(username);
            if (existing != null && existing.equals(session)) {
                userSessions.remove(username);
                System.out.println("WebSocket closed for user: " + username);
            }
        } catch (NumberFormatException ignored) {
            // Do nothing
        }
    }

    @OnError
    public void onError(Session session, @PathParam("username") String usernameStr, Throwable throwable) {
        try {
            Integer username = Integer.valueOf(usernameStr);
            Session existing = userSessions.get(username);
            if (existing != null && existing.equals(session)) {
                userSessions.remove(username);
            }
            System.err.println("WebSocket error for user " + username + ": " + throwable.getMessage());
        } catch (NumberFormatException ignored) {
            System.err.println("WebSocket error with invalid username: " + throwable.getMessage());
        }
    }

    public void sendMessage(Integer username, Integer notificationPhase, String notificationDescription) {
        System.out.println("Sending notification to user: " + username);
        String message = JsonbBuilder.create().toJson(Map.of(
                "message", notificationDescription,
                "phase", notificationPhase
        ));

        Session session = userSessions.get(username);
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
