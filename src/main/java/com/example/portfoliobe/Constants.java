package com.example.portfoliobe;

import org.apache.kafka.common.protocol.types.Field;

public final class Constants {
    public static class REQUEST_NAME{
        public static final String CHECK_MNP = "Check MNP";
        public static final String SEND_OTP = "Send OTP";
        public static final String VERIFY_OTP = "Verify OTP";
        public static final String CREDIT_SCORE_QUERY = "Credit score query";
        public static final String FRAUD_SCORE_QUERY = "Fraud score query";
        public static final String SEND_SMS_CONFIRM = "Send SMS confirm";
        public static final String CHECK_SMS_CONFIRM = "Check SMS confirm";
    }
}
