package cz.memsource.entrytest.token;

/**
 * Service providing Memsource API Token.
 */
public interface ApiTokenProvider {

    /**
     * @return Memsource API Token
     */
    String getApiToken();
}
