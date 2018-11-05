package es.home.example.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/groupChat/{group}/{user}")
public class GroupChatWebsocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupChatWebsocket.class);

    private Map<String, Integer> counters = new HashMap<>();
    private List<Session> sessions = new ArrayList<>();

    private final static String REGEX = "\\/groupChat\\/([\\w]+)\\/([\\w]+)";
    private final static String WELCOME_MESSAGE = "Welcome to the %s chat, %s. There are: %d people";
    private final static String HAS_JOINED = "%s has joined";
    private final static String SAYS = "%s says: %s";
    private final static String BYE = "Bye %s... Now we're %s people";

    @OnOpen
    public void opening(@PathParam("group") final String group, @PathParam("user") final String user, final Session session) {
        addUserCount(group);
        String message = String.format(WELCOME_MESSAGE, group, user, counters.get(group));
        LOGGER.info(message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException except) {
            LOGGER.error(except.getMessage(), except);
        }
        broadcast(String.format(HAS_JOINED, user), group);
        sessions.add(session);
    }

    @OnMessage
    public void onTextMessage(@PathParam("group") final String group, @PathParam("user") final String user, final String text,
            final Session session) throws IOException {
        String message = String.format(SAYS, user, text);
        LOGGER.info(message);
        broadcast(message, group);
    }

    @OnClose
    public void onClose(@PathParam("group") final String group, @PathParam("user") final String user,
            final CloseReason closeReason, final Session session) {
        removeUserCount(group);
        String message = String.format(BYE, user, counters.get(group));
        broadcast(message, group);
        sessions.remove(session);
    }

    @OnError
    public void onError(final Throwable throwable) {
        LOGGER.info("Ooops, we had an error: " + throwable.getMessage());
        LOGGER.error(throwable.getMessage(), throwable);
    }

    private boolean isFromYourGroup(final Session session, final String group) {
        boolean exit = false;
        Pattern pattern4 = Pattern.compile(REGEX);
        Matcher matcher = pattern4.matcher(session.getRequestURI().toString());
        if (matcher.find() && group.equals(matcher.group(1))) {
            exit = true;
        }
        return exit;
    }

    private void broadcast(final String text, final String group) {
        sessions.forEach(session -> {
            try {
                if (isFromYourGroup(session, group)) {
                    session.getBasicRemote().sendText(text);
                }
            } catch (IOException except) {
                LOGGER.error(except.getMessage(), except);
            }
        });
    }

    private void addUserCount(final String group) {
        Integer count = 1;
        if (counters.containsKey(group)) {
            count = counters.get(group) + 1;
        }
        counters.put(group, count);
    }

    private void removeUserCount(final String group) {
        Integer count = 0;
        if (counters.containsKey(group)) {
            count = counters.get(group) - 1;
        }
        counters.put(group, count);
    }
}
