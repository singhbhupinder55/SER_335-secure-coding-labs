package org.jfm.main;

import java.io.FileReader;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class WhitelistUtil {
    private static final Map<String, List<String>> whitelistMap = new HashMap<>();

    static {
        loadWhitelist();
    }

    private static void loadWhitelist() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray whitelistData = (JSONArray) parser.parse(new FileReader("resources/whitelist.json"));

            for (Object obj : whitelistData) {
                JSONObject roleData = (JSONObject) obj;
                String role = (String) roleData.get("role");
                JSONArray terms = (JSONArray) roleData.get("whitelist");

                List<String> allowedCommands = new ArrayList<>();
                for (Object term : terms) {
                    allowedCommands.add((String) term);
                }
                whitelistMap.put(role, allowedCommands);
            }
        } catch (Exception e) {
            System.err.println("Error loading whitelist.json: " + e.getMessage());
        }
    }

    public static boolean isAllowed(String role, String command) {
        List<String> allowed = whitelistMap.getOrDefault(role, Collections.emptyList());
        return allowed.contains(command.split(" ")[0]); // Only allow base command
    }
}
