package com.st.physiobiometrics.data;


import android.content.Context;

import com.st.physiobiometrics.data.model.LoggedInClient;

/**
 * Class that requests authentication and client information from the remote data source and
 * maintains an in-memory cache of login status and client credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If client credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInClient client = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return client != null;
    }

    public void logout() {
        client = null;
        dataSource.logout();
    }

    private void setLoggedInClient(LoggedInClient client) {
        this.client = client;
        // If client credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInClient> login(Context context, String clinic, String client) {
        // handle login
        Result<LoggedInClient> result = dataSource.login(context, clinic, client);
        if (result instanceof Result.Success) {
            setLoggedInClient(((Result.Success<LoggedInClient>) result).getData());
        }
        return result;
    }
}