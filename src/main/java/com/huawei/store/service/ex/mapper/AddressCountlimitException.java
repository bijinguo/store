package com.huawei.store.service.ex.mapper;

import com.huawei.store.service.ex.ServiceException;

public class AddressCountlimitException extends ServiceException {
    public AddressCountlimitException() {
        super();
    }

    public AddressCountlimitException(String message) {




    }

    public AddressCountlimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountlimitException(Throwable cause) {
        super(cause);
    }

    protected AddressCountlimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
