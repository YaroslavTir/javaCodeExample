package ru.fls.privateoffice.util.service.richbanner;

import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * @author YMolodkov
 * @since 14.0
 */
public class CommonCheckerTest {
    private CommonChecker checker = new CommonChecker();

    @Test
    public void testReplaceSpecialSymbols() throws Exception {
        assertEquals("Unexpected result for replaceSpecialSymbols method", "\\Qbbb\\E(.*)\\Qbbbb\\E(.*)", checker.replaceSpecialSymbols("bbb*bbbb"));
        assertEquals("Unexpected result for replaceSpecialSymbols method", "\\Qbbb\\E(.*)\\Qb\\E(.*)\\Qbbb\\E", checker.replaceSpecialSymbols("bbb*b*bbb_"));
        assertEquals("Unexpected result for replaceSpecialSymbols method", "\\Qbbb\\E(.{1})\\Qbb\\E(.*)", checker.replaceSpecialSymbols("bbb?bb"));
        assertEquals("Unexpected result for replaceSpecialSymbols method", "\\Qbb\\E(.*)\\Qb\\E(.{1})\\Qbb\\E(.*)", checker.replaceSpecialSymbols("bb*b?bb"));
    }

    @Test
    public void testArrStringCheck() throws Exception {
        String[] arrString = new String[]{"A", "B", "C"};
        String[] emptyArrString = new String[]{};
        assertTrue("Unexpected result for arrStringCheck method", checker.arrStringCheck(arrString, "A"));
        assertTrue("Unexpected result for arrStringCheck method", checker.arrStringCheck(emptyArrString, "A"));
        assertTrue("Unexpected result for arrStringCheck method", checker.arrStringCheck(null, "A"));
        assertFalse("Unexpected result for arrStringCheck method", checker.arrStringCheck(arrString, "D"));
    }

    @Test
    public void testStrByFormatCheck() throws Exception {
        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("*+35?@firstlinesoftware.com", "inessa.makarchuk+359@firstlinesoftware.com"));
        assertFalse("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("*+5?@firstlinesoftware.com", "inessa.makarchuk+349@firstlinesoftware.com"));

        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("javaIsAwesome@dev.com", "javaIsAwesome@dev.com"));
        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("java*", "javaIsAwesome"));
        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("   java*Awesome@dev.com", "javaIsAwesome@dev.com"));
        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("*", "javaIsAwesome@dev.com"));

        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("java?sAwesome@dev.com", "javaIsAwesome@dev.com"));
        assertFalse("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("java?Awesome@dev.com", "javaIsAwesome@dev.com"));

        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("java?sAwesome@dev.com", "javaIsAwesome@dev.com"));
        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("j", "javaIsAwesome@dev.com"));
        assertFalse("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("j_", "javaIsAwesome@dev.com"));

        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck(null, "javaIsAwesome@dev.com"));
        assertTrue("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("", "javaIsAwesome@dev.com"));
        assertFalse("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("javaIsAwesome@dev.com", null));

        assertFalse("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("cppIsAwesome@dev.com", "javaIsAwesome@dev.com"));
        assertFalse("Unexpected result for strByFormatCheck method", checker.strByFormatCheck("javaIsAwesome@dev.com", "cppIsAwesome@dev.com"));

    }

    @Test
    public void testStringCheck() throws Exception {
        assertTrue("Unexpected result for arrStringCheck method", checker.stringCheck(null, "test1"));
        assertFalse("Unexpected result for arrStringCheck method", checker.stringCheck("test1", null));
        assertTrue("Unexpected result for arrStringCheck method", checker.stringCheck("asdf", "asdf"));
        assertFalse("Unexpected result for arrStringCheck method", checker.stringCheck("test1", "test2"));
    }

    @Test
    public void testFioStringCheck() throws Exception {
        assertTrue("Unexpected result for fioStringCheck method", checker.fioStringCheck("bander", "BanDer"));
        assertTrue("Unexpected result for fioStringCheck method", checker.fioStringCheck("bander", "BANDER"));
        assertTrue("Unexpected result for fioStringCheck method", checker.fioStringCheck(null, "test"));
        assertTrue("Unexpected result for fioStringCheck method", checker.fioStringCheck(null, null));
        assertTrue("Unexpected result for fioStringCheck method", checker.fioStringCheck("band*", "BANDER"));
        assertFalse("Unexpected result for fioStringCheck method", checker.fioStringCheck("bander", "test"));
    }

    @Test
    public void testBooleanCheck() throws Exception {
        assertTrue("Unexpected result for booleanCheck method", checker.booleanCheck(null, null));
        assertTrue("Unexpected result for booleanCheck method", checker.booleanCheck(null, false));
        assertFalse("Unexpected result for booleanCheck method", checker.booleanCheck(true, null));
        assertTrue("Unexpected result for booleanCheck method", checker.booleanCheck(false, false));
        assertFalse("Unexpected result for booleanCheck method", checker.booleanCheck(true, false));
    }

    @Test
    public void testStringListCheck() throws Exception {
        List<String> stringList = Arrays.asList("A", "B", "C");
        List<String> emptyStringList = Collections.EMPTY_LIST;
        assertFalse("Unexpected result for stringListCheck method", checker.stringListCheck("A", null));
        assertTrue("Unexpected result for stringListCheck method", checker.stringListCheck("A", stringList));
        assertTrue("Unexpected result for stringListCheck method", checker.stringListCheck(null, stringList));
        assertFalse("Unexpected result for stringListCheck method", checker.stringListCheck("A", emptyStringList));
        assertFalse("Unexpected result for stringListCheck method", checker.stringListCheck("D", stringList));
    }

    @Test
    public void testDateFromCheck() throws Exception {
        Date currentDate = new Date();
        LocalDate dateAfter = (new LocalDate(currentDate)).withFieldAdded(DurationFieldType.days(), 1);
        LocalDate dateBefore = (new LocalDate(currentDate)).withFieldAdded(DurationFieldType.days(), -1);
        currentDate = (new LocalDateTime(currentDate)).toDate();
        assertFalse("Unexpected result for dateFromCheck method", checker.dateFromCheck(currentDate, null));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(null, currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(dateBefore.toDate(), currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(currentDate, currentDate));
        assertFalse("Unexpected result for dateFromCheck method", checker.dateFromCheck(dateAfter.toDate(), currentDate));


        DateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:dd:ss");
        Date date = (new LocalDateTime(formatter.parse("01/29/02 10:10:10"))).toDate();
        LocalDateTime dateTimeAfter = (new LocalDateTime(date)).withFieldAdded(DurationFieldType.seconds(), 1);
        LocalDateTime dateTimeBefore = (new LocalDateTime(date)).withFieldAdded(DurationFieldType.seconds(), -1);

        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(dateTimeBefore.toDate(), currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(currentDate, currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(dateTimeAfter.toDate(), currentDate));

    }

    @Test
    public void testDateToCheck() throws Exception {
        Date currentDate = new Date();
        LocalDate dateAfter = (new LocalDate(currentDate)).withFieldAdded(DurationFieldType.days(), 1);
        LocalDate dateBefore = (new LocalDate(currentDate)).withFieldAdded(DurationFieldType.days(), -1);
        currentDate = (new LocalDateTime(currentDate)).toDate();
        assertTrue("Unexpected result for dateToCheck method", checker.dateToCheck(null, currentDate));
        assertFalse("Unexpected result for dateToCheck method", checker.dateToCheck(currentDate, null));
        assertTrue("Unexpected result for dateToCheck method", checker.dateToCheck(dateAfter.toDate(), currentDate));
        assertTrue("Unexpected result for dateToCheck method", checker.dateToCheck(currentDate, currentDate));
        assertFalse("Unexpected result for dateToCheck method", checker.dateToCheck(dateBefore.toDate(), currentDate));

        DateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:dd:ss");
        Date date = (new LocalDateTime(formatter.parse("01/29/02 10:10:10"))).toDate();
        LocalDateTime dateTimeAfter = (new LocalDateTime(date)).withFieldAdded(DurationFieldType.seconds(), 1);
        LocalDateTime dateTimeBefore = (new LocalDateTime(date)).withFieldAdded(DurationFieldType.seconds(), -1);

        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(dateTimeBefore.toDate(), currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(currentDate, currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateFromCheck(dateTimeAfter.toDate(), currentDate));
    }


    @Test
    public void testDateTimeFromCheck() throws Exception {
        Date currentDate = new Date();
        LocalDateTime dateAfter = (new LocalDateTime(currentDate)).withFieldAdded(DurationFieldType.seconds(), 1);
        LocalDateTime dateBefore = (new LocalDateTime(currentDate)).withFieldAdded(DurationFieldType.seconds(), -1);
        currentDate = (new LocalDateTime(currentDate)).toDate();
        assertFalse("Unexpected result for dateFromCheck method", checker.dateTimeFromCheck(currentDate, null));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateTimeFromCheck(null, currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateTimeFromCheck(dateBefore.toDate(), currentDate));
        assertTrue("Unexpected result for dateFromCheck method", checker.dateTimeFromCheck(currentDate, currentDate));
        assertFalse("Unexpected result for dateFromCheck method", checker.dateTimeFromCheck(dateAfter.toDate(), currentDate));
    }

    @Test
    public void testDateTimeToCheck() throws Exception {
        Date currentDate = new Date();
        LocalDateTime dateAfter = (new LocalDateTime(currentDate)).withFieldAdded(DurationFieldType.seconds(), 1);
        LocalDateTime dateBefore = (new LocalDateTime(currentDate)).withFieldAdded(DurationFieldType.seconds(), -1);
        currentDate = (new LocalDateTime(currentDate)).toDate();
        assertTrue("Unexpected result for dateToCheck method", checker.dateTimeToCheck(null, currentDate));
        assertFalse("Unexpected result for dateToCheck method", checker.dateTimeToCheck(currentDate, null));
        assertTrue("Unexpected result for dateToCheck method", checker.dateTimeToCheck(dateAfter.toDate(), currentDate));
        assertTrue("Unexpected result for dateToCheck method", checker.dateTimeToCheck(currentDate, currentDate));
        assertFalse("Unexpected result for dateToCheck method", checker.dateTimeToCheck(dateBefore.toDate(), currentDate));
    }


}
