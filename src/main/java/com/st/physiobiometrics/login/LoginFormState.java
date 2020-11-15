package com.st.physiobiometrics.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
public class LoginFormState {
    @Nullable
    private Integer clinicError;
    @Nullable
    private Integer clientError;
    private boolean isDataValid;

    LoginFormState(@Nullable Integer clinicError, @Nullable Integer clientError) {
        this.clinicError = clinicError;
        this.clinicError = clinicError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.clinicError = null;
        this.clinicError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getclinicError() {
        return clinicError;
    }

    @Nullable
    public Integer getclientError() {
        return clinicError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}