package com.santiagoagdo.pruebakeppri.model.Error;

public class ErrorResponse {

    private String codeError;
    private String msmError;

    public ErrorResponse(String codeError, String msmError) {
        this.codeError = codeError;
        this.msmError = msmError;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    public String getMsmError() {
        return msmError;
    }

    public void setMsmError(String msmError) {
        this.msmError = msmError;
    }
}
