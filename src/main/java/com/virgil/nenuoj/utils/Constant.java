package com.virgil.nenuoj.utils;

import lombok.val;

public class Constant {

    public enum Account {
        CODE_CHANGE_PASSWORD_FAIL("change-password-fail:"),
        CODE_CHANGE_PASSWORD_LOCK("change-password-lock:"),
        CODE_ACCOUNT_LOCK("account-lock:"),
        CODE_CHANGE_EMAIL_FAIL("change-email-fail:"),
        CODE_CHANGE_EMAIL_LOCK("change-email-lock:"),

        TRY_LOGIN_NUM("try-login-num:"),
        SEND_REGISTER_CODE("send-register-code:"),
        TOKEN_CHECK("token-check:"),

        ACM_RANK_CACHE("acm_rank_cache"),
        OI_RANK_CACHE("oi_rank_cache"),

        GROUP_RANK_CACHE("group_rank_cache"),

        SUPER_ADMIN_UID_LIST_CACHE("super_admin_uid_list_case"),

        SUBMIT_NON_CONTEST_LOCK("submit_non_contest_lock:"),
        TEST_JUDGE_LOCK("test_judge_lock:"),
        SUBMIT_CONTEST_LOCK("submit_contest_lock:"),
        DISCUSSION_ADD_NUM_LOCK("discussion_add_num_lock:"),
        GROUP_ADD_NUM_LOCK("group_add_num_lock"),
        CONTEST_ADD_PRINT_LOCK("contest_add_print_lock:"),

        REMOTE_JUDGE_CF_ACCOUNT_NUM("remote_judge_cf_account:");

        private final String code;

        Account(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum Email{
        OJ_NAME("NENU Online Judge"),
        OJ_SHORT_NAME("NENUOJ"),
        OJ_URL("OJ_URL"),
        EMAIL_BACKGROUND_IMG("EMAIL_BACKGROUND_IMG"),
        EMAIL_CODE("CODE"),
        EMAIL_EXPIRE_TIME("EXPIRE_TIME")
        ;

        private final String val;
        Email( String  val ) {
            this.val = val;
        }

        public String getVal() {
            return val;
        }

    }

}
