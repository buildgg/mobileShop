package ua.home.mobileshop.util;

public class UriRequest {

    public static boolean isStaticUrl(String uri) {
        return uri.startsWith("/static/");
    }
    public static boolean isMediaUrl(String uri) {
        return uri.startsWith("/media/");
    }
    public static boolean isAjaxUrl(String url) {
        return url.startsWith("/ajax/");
    }

    public static boolean isAjaxJsonUrl(String url) {
        return url.startsWith("/ajax/json/");
    }

    public static boolean isAjaxHtmlUrl(String url) {
        return url.startsWith("/ajax/html/");
    }

    public static boolean isCurrentPageRedirect(String url){
         return url.startsWith("/products")|| url.startsWith("/search")||url.startsWith("/shopping-cart");
    }
    private UriRequest(){

    }
}
