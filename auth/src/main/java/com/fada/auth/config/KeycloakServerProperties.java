package com.fada.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@ConfigurationProperties(prefix = "keycloak.server")
@Getter
@Setter
public class KeycloakServerProperties {

    String contextPath;

    String realmImportFile;

    AdminUser adminUser;

    String googleId;

    String googleSecret;

    @Getter
    @Setter
    public static class AdminUser {

        String username;

        String password;
    }
}
