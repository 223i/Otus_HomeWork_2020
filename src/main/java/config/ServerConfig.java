package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {

    @Key("url")
    String url();

    @Key("userEmail")
    String userEmail();

    @Key("userPassword")
    String userPassword();

    @Key("urlPersonalAccountBiography")
    String urlPersonalAccountBiography();

    @Key("telegramContact")
    String telegramContact();

    @Key("skypeContact")
    String skypeContact();
}