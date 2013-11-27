/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.data;

import com.sky.detector.exceptions.InvalidInputStringFormatException;
import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoglineAsLoginAttemptTest {
    public static final String SOME_STRING = "somestring";
    public static final String SOME_IP = "192.168.0.1";
    public static final String SOME_DATE = "133612947";
    public static final String SOME_ACTION = Action.SIGNIN_FAILURE.toString();
    public static final String SOME_USERNAME = "Dave.Branning";
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void fail_with_invalid_syntax_if_logline_does_not_have_4_tokens() {
        asLoginAttempt(SOME_STRING);
    }
    
    @Test
    public void first_token_is_login_attempt_ip() {
        String firstToken = SOME_IP;
        String logLine = loglineWithPresetToken(0, firstToken);
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getIp(), is(firstToken));
    }
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void fail_with_invalid_syntax_if_ip_is_invalid() {
        String ipToken = SOME_STRING;
        String logLine = loglineWithPresetToken(0, ipToken);
        
        asLoginAttempt(logLine);
    }
    
    @Test
    public void second_token_is_login_attempt_time() {
        Date secondToken = new Date(100);
        String logLine = loglineWithPresetToken(1, secondToken.getTime()+"");
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getDate(), is(secondToken));
    }
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void fail_with_invalid_syntax_if_date_is_invalid() {
        String dateToken = SOME_STRING;
        String logLine = loglineWithPresetToken(1, dateToken);
        
        asLoginAttempt(logLine);
    }
    
    @Test
    public void third_token_is_login_attempt_action() {
        Action thirdToken = Action.SIGNIN_FAILURE;
        String logLine = loglineWithPresetToken(2, thirdToken.toString());
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getAction(), is(thirdToken));
    }
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void fail_with_invalid_syntax_if_action_is_invalid() {
        String actionToken = SOME_STRING;
        String logLine = loglineWithPresetToken(2, actionToken);
        
        asLoginAttempt(logLine);
    }
    
    @Test
    public void fourth_token_is_login_attempt_username() {
        String fourthToken = SOME_STRING;
        String logLine = loglineWithPresetToken(3, fourthToken);
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getUsername(), is(fourthToken));
    }
    

    //~~~~~~~ Test helpers
    
    private LoginAttempt asLoginAttempt(String logLine) {
        return new LoglineAsLoginAttempt().convert(logLine);
    }    
    
    
    private String loglineWithPresetToken(int tokenPosition, String token) {
        StringBuilder st = new StringBuilder();
        String[] tokens = {SOME_IP, SOME_DATE, SOME_ACTION, SOME_USERNAME};
        tokens[tokenPosition] = token;
        
        for (String localToken : tokens) {
            st.append(localToken);
            st.append(LoglineAsLoginAttempt.TOKEN_SEPARATOR);
        }
        return st.toString();
    }
}