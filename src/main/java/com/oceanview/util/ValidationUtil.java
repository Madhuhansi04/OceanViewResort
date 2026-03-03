package com.oceanview.util;

public class ValidationUtil {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isValidContact(String contact) {
        return contact != null && contact.matches("^[0-9]{10}$");
    }

    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidDate(String date) {
        try {
            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
