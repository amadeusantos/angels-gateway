package com.angels.eurekaserver.config;

//import jakarta.ws.rs.client.ClientRequestContext;
//import jakarta.ws.rs.client.ClientRequestFilter;
//
//import java.io.IOException;
//
//public class BasicAuthenticationFilter implements ClientRequestFilter {
//
//    private final static String USER = "user";
//
//    private final static String PASSWORD = "password";
//
//    private final static String AUTHORIZATION = USER + "=" + PASSWORD;
//
//    @Override
//    public void filter(ClientRequestContext clientRequestContext) throws IOException {
//        final String authHeader = clientRequestContext.getHeaderString("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Basic ")) {
//            throw new IOException("unauthorized client");
//        }
//
//        String user = authHeader.substring(6); // "Basic "
//        if (!user.equals(AUTHORIZATION)) {
//            throw new IOException("unauthorized client");
//        }
//    }
//}
