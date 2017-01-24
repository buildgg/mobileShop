package ua.home.mobileshop.service;

import ua.home.mobileshop.model.SocialAccount;

/**
 * Created by vov on 20.01.2017.
 */
public interface SocialService {
    String getAuthorizeUrl();
    SocialAccount getSocialAccount(String authToken);
}
