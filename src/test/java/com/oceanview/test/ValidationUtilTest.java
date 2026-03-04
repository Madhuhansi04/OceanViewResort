package com.oceanview.test;

import com.oceanview.util.ValidationUtil;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationUtilTest {

    @Test
    public void testValidContact() {
        assertTrue(ValidationUtil.isValidContact("0771234567"));
    }

    @Test
    public void testInvalidContactShort() {
        assertFalse(ValidationUtil.isValidContact("077123"));
    }

    @Test
    public void testInvalidContactLetters() {
        assertFalse(ValidationUtil.isValidContact("077ABCDEFG"));
    }

    @Test
    public void testInvalidContactNull() {
        assertFalse(ValidationUtil.isValidContact(null));
    }

    @Test
    public void testInvalidContactEmpty() {
        assertFalse(ValidationUtil.isValidContact(""));
    }

    @Test
    public void testInvalidContactSpaces() {
        assertFalse(ValidationUtil.isValidContact("077 123 456"));
    }

    @Test
    public void testInvalidContactTooLong() {
        assertFalse(ValidationUtil.isValidContact("07712345678"));
    }

    @Test
    public void testValidEmail() {
        assertTrue(ValidationUtil.isValidEmail("test@gmail.com"));
    }

    @Test
    public void testValidEmailYahoo() {
        assertTrue(ValidationUtil.isValidEmail("test@yahoo.com"));
    }

    @Test
    public void testInvalidEmailNoAt() {
        assertFalse(ValidationUtil.isValidEmail("testgmail.com"));
    }

    @Test
    public void testInvalidEmailNoDomain() {
        assertFalse(ValidationUtil.isValidEmail("test@"));
    }

    @Test
    public void testInvalidEmailNull() {
        assertFalse(ValidationUtil.isValidEmail(null));
    }

    @Test
    public void testInvalidEmailEmpty() {
        assertFalse(ValidationUtil.isValidEmail(""));
    }

    @Test
    public void testNullOrEmpty_Null() {
        assertTrue(ValidationUtil.isNullOrEmpty(null));
    }

    @Test
    public void testNullOrEmpty_Empty() {
        assertTrue(ValidationUtil.isNullOrEmpty(""));
    }

    @Test
    public void testNullOrEmpty_Spaces() {
        assertTrue(ValidationUtil.isNullOrEmpty("   "));
    }

    @Test
    public void testNullOrEmpty_NotEmpty() {
        assertFalse(ValidationUtil.isNullOrEmpty("John"));
    }

    @Test
    public void testNullOrEmpty_WithSpaces() {
        assertFalse(ValidationUtil.isNullOrEmpty("John Silva"));
    }

    @Test
    public void testValidContactStartsWith07() {
        assertTrue(ValidationUtil.isValidContact("0712345678"));
    }

    @Test
    public void testValidContactStartsWith09() {
        assertTrue(ValidationUtil.isValidContact("0912345678"));
    }

    @Test
    public void testInvalidEmailOnlyAt() {
        assertFalse(ValidationUtil.isValidEmail("@gmail.com"));
    }

    @Test
    public void testNullOrEmpty_WhitespaceOnly() {
        assertTrue(ValidationUtil.isNullOrEmpty("     "));
    }

    @Test
    public void testValidEmailWithNumbers() {
        assertTrue(ValidationUtil.isValidEmail("test123@gmail.com"));
    }

}
