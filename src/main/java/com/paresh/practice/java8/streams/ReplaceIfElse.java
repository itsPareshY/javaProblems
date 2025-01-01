package com.paresh.practice.java8.streams;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ReplaceIfElse {

    /**
     * public class RoleAssigner {
     *     public String getRole(String userType) {
     *         if ("admin".equals(userType)) {
     *             return "Administrator";
     *         } else if ("moderator".equals(userType)) {
     *             return "Moderator";
     *         } else if ("user".equals(userType)) {
     *             return "Regular User";
     *         } else {
     *             return "Guest";
     *         }
     *     }
     * }
     */

    private static final Map<Integer, String> roleMap = new HashMap<>();
           static {
               roleMap.put(1, "Administrator");
               roleMap.put(2,"Developer");
               roleMap.put(3,"Moderator");
           }


    public String getRole(Integer userType) {
        return roleMap.entrySet().stream().filter(entry -> entry.getKey().equals(userType))
                .map(Map.Entry::getValue)
                .findFirst().orElse("Guest");
        //here we removed the need to do if-else on userType to match and return the string type
    }
}
