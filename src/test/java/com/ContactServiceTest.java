package com;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        System.out.println("This is run for every test method");
    }

    @BeforeAll
    public static void setupAll() {
        System.out.println("This should be printed before all classes");
    }

    @Test
    @DisplayName("Should create contact")
    void testCreateContact() {
        service.addContact("Prathima", "Y", "0123456789");
        assertFalse(service.contactList.isEmpty());
        assertEquals(1, service.contactList.size());
    }

    @Test
    @DisplayName("Runtime exception should be thrown when first name null or blank")
    void testFirstNameValidation() {
        Assertions.assertThrows(RuntimeException.class, () ->
        {
            service.addContact(null, "ya", "0123456789");
        });
    }

    @Test
    @DisplayName("Runtime exception should be thrown when last name is null or blank")
    void testLastNameValidation() {
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    service.addContact("Prathima", "  ", "0123456789");
                });
    }

    @Test
    @DisplayName("Runtime exception thrown when phone number null or blank")
    void testPhoneNumberValidation() {
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    service.addContact("Prathima", "Ya", "  ");
                });
    }

    @Test
    @DisplayName("create contact on mac")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Should run only on mac")
    void testCreateContactMac() {
        service.addContact("Prathima", "Y", "0123456789");
        assertFalse(service.contactList.isEmpty());
        assertEquals(1, service.contactList.size());
    }

    @Test
    @DisplayName("create contact on dev m/c")
    void testCreateContactDevMc() {
        System.out.println("Env prop is " + System.getProperty("ENV"));
        Assumptions.assumeTrue("DEV".equals(System
                .getProperty("ENV")));
        service.addContact("Prathima", "Y", "0123456789");
        assertFalse(service.contactList.isEmpty());
        assertEquals(1, service.contactList.size());
    }

    @Test
    @DisplayName("This is a disabled test")
    @Disabled
    void testCreateContactDisabled() {
        service.addContact("Prathima", "Ya", "0123");
        Assertions.assertTrue(!service.contactList.isEmpty());

    }

    @Nested
    class RepeatedTests {
        @RepeatedTest(value = 5, name = "repetition {currentRepetition} of {totalRepetition}")
        @DisplayName("This test is repeated 5 times")
        void testCreateContactRepeated() {
            service.addContact("Prathima", "Y", "0123456789");
            Assertions.assertTrue(!service.contactList.isEmpty());
            Assertions.assertEquals(1, service.contactList.size());
        }
    }

    @Nested
    class ParameterizedTests {

        @DisplayName("This is parameterized test for values of phone number")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "4534535", "sdf45345"})
        void testMultiplePhone(String phoneNumber) {
            service.addContact("Prathima", "Y", phoneNumber);
            Assertions.assertEquals(1, service.contactList.size());
            Assertions.assertTrue(!service.contactList.isEmpty());
        }

        @DisplayName("This is parameterized for values of phone number")
        @ParameterizedTest
        @CsvSource({"2354234234", "023423452345345", "sdfsf"})
        void testMultiPhoneNumCSV(String phoneNumber) {
            service.addContact("Prathima", "Y", phoneNumber);
            Assertions.assertTrue(!service.contactList.isEmpty());
            Assertions.assertEquals(1, service.contactList.size());
        }

        @DisplayName("This is parameterized for values of phone number")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        void testMultiPhoneNumCSVFile(String phoneNumber) {
            service.addContact("Prathima", "Y", phoneNumber);
            Assertions.assertTrue(!service.contactList.isEmpty());
            Assertions.assertEquals(1, service.contactList.size());
        }
    }

    @DisplayName("Parameterized test with method source")
    @ParameterizedTest
    @MethodSource("numbersList")
    void testMultiplePhoneNumbers(String phoneNumber) {
        service.addContact("Prathima", "Y", phoneNumber);
        Assertions.assertEquals(1, service.contactList.size());
        Assertions.assertTrue(!service.contactList.isEmpty());
    }

    static List<String> numbersList() {
        return Arrays.asList("1223345", "0123456789", "dfsdffghdu", null);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Executes after every test method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Executes after all tests are done");

    }
}
