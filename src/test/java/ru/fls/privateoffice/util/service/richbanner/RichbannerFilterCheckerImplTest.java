package ru.fls.privateoffice.util.service.richbanner;

import org.junit.Test;
import ru.fls.privateoffice.util.dto.ProgramElementDTO;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.junit.Test;
import ru.fls.privateoffice.util.service.richbanner.RichbannerFilterChecker;
import ru.fls.privateoffice.util.service.richbanner.RichbannerFilterCheckerImpl;
import ru.fls.privateoffice.utils.DateFormatter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import java.util.List;

/**
 * @author YMolodkov
 * @since 14.0
 */
public class RichbannerFilterCheckerImplTest {

    private RichbannerFilterChecker checker = new RichbannerFilterCheckerImpl(new CommonChecker());


    @Test
    public void testBalanceFromCheck() throws Exception {
        assertTrue("Unexpected result for balanceFromCheck method", checker.balanceFromCheck(null, null));
        assertTrue("Unexpected result for balanceFromCheck method", checker.balanceFromCheck(null, new BigDecimal(12L)));
        assertTrue("Unexpected result for balanceFromCheck method", checker.balanceFromCheck(12L, new BigDecimal(12L)));
        assertTrue("Unexpected result for balanceFromCheck method", checker.balanceFromCheck(11L, new BigDecimal(12L)));
        assertFalse("Unexpected result for balanceFromCheck method", checker.balanceFromCheck(13L, new BigDecimal(12L)));
    }

    @Test
    public void testBalanceToCheck() throws Exception {
        assertTrue("Unexpected result for balanceToCheck method", checker.balanceToCheck(null, null));
        assertTrue("Unexpected result for balanceToCheck method", checker.balanceToCheck(null, new BigDecimal(12L)));
        assertTrue("Unexpected result for balanceToCheck method", checker.balanceToCheck(12L, new BigDecimal(12L)));
        assertFalse("Unexpected result for balanceToCheck method", checker.balanceToCheck(11L, new BigDecimal(12L)));
        assertTrue("Unexpected result for balanceToCheck method", checker.balanceToCheck(13L, new BigDecimal(12L)));
    }

    @Test
    public void testProgramsCheck() throws Exception {
        String[] mask = null;
        List<ProgramElementDTO> programs = null;
        assertTrue("Unexpected result for programsCheck method", checker.programsCheck(mask, programs));
        mask =new String[]{"1", "2", "3"};
        assertFalse("Unexpected result for programsCheck method", checker.programsCheck(mask, programs));
        programs = Arrays.asList(new ProgramElementDTO("4"), new ProgramElementDTO("5"));
        assertFalse("Unexpected result for programsCheck method", checker.programsCheck(mask, programs));
        programs = Arrays.asList(new ProgramElementDTO("1"), new ProgramElementDTO("5"));
        assertTrue("Unexpected result for programsCheck method", checker.programsCheck(mask, programs));
    }

    @Test
    public void testBirthDayCheck() throws Exception {
        DateFormatter dateFormatter = new DateFormatter();
        assertFalse("Unexpected result for birthDayCheck method", checker.birthDayCheck(1, null));
        assertTrue("Unexpected result for birthDayCheck method", checker.birthDayCheck(null, dateFormatter.parseDate("01.11.2000 23:12")));
        assertTrue("Unexpected result for birthDayCheck method", checker.birthDayCheck(1, dateFormatter.parseDate("01.11.2000 23:12")));
        assertFalse("Unexpected result for birthDayCheck method", checker.birthDayCheck(1, dateFormatter.parseDate("21.11.2000 23:12")));
    }

    @Test
    public void testBirthMonthCheck() throws Exception {
        DateFormatter dateFormatter = new DateFormatter();
        assertFalse("Unexpected result for birthMonthCheck method", checker.birthMonthCheck(1, null));
        assertTrue("Unexpected result for birthMonthCheck method", checker.birthMonthCheck(null, dateFormatter.parseDate("01.11.2000 23:12")));
        assertTrue("Unexpected result for birthMonthCheck method", checker.birthMonthCheck(4, dateFormatter.parseDate("01.05.2000 23:12")));
        assertFalse("Unexpected result for birthMonthCheck method", checker.birthMonthCheck(1, dateFormatter.parseDate("21.05.2000 23:12")));
    }

    @Test
    public void testBirthYearCheck() throws Exception {
        DateFormatter dateFormatter = new DateFormatter();
        assertFalse("Unexpected result for birthYearCheck method", checker.birthYearCheck(1, null));
        assertTrue("Unexpected result for birthYearCheck method", checker.birthYearCheck(null, dateFormatter.parseDate("02.09.2000 23:12")));
        assertTrue("Unexpected result for birthYearCheck method", checker.birthYearCheck(2000, dateFormatter.parseDate("02.09.2000 23:12")));
        assertFalse("Unexpected result for birthYearCheck method", checker.birthYearCheck(2001, dateFormatter.parseDate("21.09.2000 23:12")));
    }

    @Test
    public void testTodayIsBirthday() throws Exception {
        Date currentDate = new Date();
        LocalDate dateAfter = (new LocalDate(currentDate)).withFieldAdded(DurationFieldType.days(), 1);
        LocalDate dateBefore = (new LocalDate(currentDate)).withFieldAdded(DurationFieldType.days(), -1);

        Calendar curBirthdayCal = Calendar.getInstance();
        curBirthdayCal.setTime(currentDate);
        curBirthdayCal.set(Calendar.YEAR, 1992);
        Date birthdayDate = curBirthdayCal.getTime();

        assertTrue("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(null, birthdayDate));
        assertFalse("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(false, null));
        assertFalse("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(true, null));

        assertTrue("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(true, birthdayDate));
        assertFalse("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(true, dateAfter.toDate()));
        assertFalse("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(true, dateBefore.toDate()));

        assertFalse("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(false, birthdayDate));
        assertTrue("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(false, dateAfter.toDate()));
        assertTrue("Unexpected result for todayIsBirthday method", checker.todayIsBirthday(false, dateBefore.toDate()));

    }

    @Test
    public void testAccountExposuresCheck() throws Exception {
        assertTrue("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(null, 2, false));
        assertFalse("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(0, null, false));
        assertTrue("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(2, null, false));
        assertTrue("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(4, 2, false));
        assertTrue("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(3, 2, false));
        assertFalse("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(2, 2, null));
        assertFalse("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(1, 2, false));
//
        assertFalse("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(2, 1, true));
        assertTrue("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(2, 0, true));
        assertTrue("Unexpected result for accountExposuresCheck method", checker.accountExposuresCheck(2, null, true));
    }
}
