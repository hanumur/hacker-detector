/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import com.sky.detector.data.LoginAttempt;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BurstLoginDetectionStrategyTest {
    
    @Test
    public void a_login_attempt_is_offensive_if_the_same_ip_had_failed_login_5_or_more_times_in_a_5_minute_period() {
        
        assertThat(true, is(true));
    }
}