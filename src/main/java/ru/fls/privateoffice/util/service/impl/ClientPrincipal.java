package ru.fls.privateoffice.util.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: NKarataeva
 * Date: 13.04.12
 * Time: 11:13
 */
public class ClientPrincipal implements Serializable {
    private static final long serialVersionUID = 2L;

    private String name;
    private String jSessionId;
    private ClientPrincipalRole role;

    private boolean changePassword;
    private boolean confirmPassword;
    private boolean passwordConfirmed;
    // todo
    // wrong date field serialization
    private String lastFeedbackSendingTime = null;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public ClientPrincipal(
            final String name,
            final String jSessionId,
            final ClientPrincipalRole role,
            final boolean changePassword,
            final boolean confirmPassword) {

        this.name = name;
        this.jSessionId = jSessionId;
        this.role = role;

        this.changePassword = changePassword;
        this.confirmPassword = confirmPassword;

    }

    public boolean checkFeedbackSendingTime() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        if (lastFeedbackSendingTime != null) {
            try {
                calendar.setTime(formatter.parse(lastFeedbackSendingTime));
            } catch (ParseException e) {
                return false;
            }
            calendar.add(Calendar.MINUTE, 10);
            Date possibleFeedbackSendingTime = calendar.getTime();
            if (possibleFeedbackSendingTime.after(now)) {
                return false;
            }
        }
        return true;
    }

    public void updateLastFeedbackSendingTime() {
        this.lastFeedbackSendingTime = formatter.format(Calendar.getInstance().getTime());
    }

    public ClientPrincipalRole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getJSessionId() {
        return jSessionId;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public boolean isConfirmPassword() {
        return confirmPassword;
    }

    public boolean isPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(boolean passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }
}
