package com.st.physiobiometrics.login;

import android.content.Context;
import android.util.Patterns;


import com.st.BlueSTSDK.gui.R;
import com.st.physiobiometrics.data.LoginRepository;
import com.st.physiobiometrics.data.Result;
import com.st.physiobiometrics.data.model.LoggedInClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(Context context, String clinic, String client) {
        // can be launched in a separate asynchronous job
        Result<com.st.physiobiometrics.data.model.LoggedInClient> result = loginRepository.login(context, clinic, client);

        if (result instanceof Result.Success) {
            LoggedInClient data = ((Result.Success<LoggedInClient>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getclient())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String clinic, String client) {
        if (!isclinicValid(clinic)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_clinic, null));
        } else if (!isclientValid(clinic)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_client));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder clinic validation check
    private boolean isclinicValid(String clinic) {
        if (clinic == null) {
            return false;
        } else {
            return !clinic.trim().isEmpty();
        }
    }

    // A placeholder clinic validation check
    private boolean isclientValid(String client) {
        if (client == null) {
            return false;
        } else {
            return !client.trim().isEmpty();
        }
    }
}