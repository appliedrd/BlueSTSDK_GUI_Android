package com.st.physiobiometrics.data;


import android.content.Context;
import android.content.SharedPreferences;

import com.st.BlueSTSDK.gui.R;
import com.st.physiobiometrics.data.model.LoggedInClient;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInClient> login(Context context, String clinic, String client) {

        try {
            SharedPreferences sharedPref = context.getSharedPreferences("CLINIC", Context.MODE_PRIVATE);
            String testClinic = sharedPref.getString("CLINIC","unknown");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("CLINIC",clinic);
            editor.putString("CLIENT",client);
            editor.commit();
            // TODO: handle LoggedInClient authentication
            String display = client + " @ " + clinic;
            LoggedInClient user =
                    new LoggedInClient(
                            java.util.UUID.randomUUID().toString(), display);

            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}