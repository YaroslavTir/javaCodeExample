package ru.fls.privateoffice.util.service.richbanner;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import ru.fls.privateoffice.dao.impl.RichbannerDaoImp;
import ru.fls.privateoffice.entity.Richbanner;
import ru.fls.privateoffice.util.dto.banner.RichbannerDTO;
import ru.rzd.loyalty.shared.json.JsonTransformer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author YMolodkov
 * @since 14.0
 */
public class RichbannerServiceTest {

    private RichbannerServiceImp richbannerService;
    private RichbannerDaoImp richbannerDaoImp;

    @Before
    public void setUp() throws Exception {
        Mapper mapper = new DozerBeanMapper();
        JsonTransformer jsonTransformer = new JsonTransformer();
        richbannerDaoImp = mock(RichbannerDaoImp.class);
        RichbannerFilterChecker checker = mock(RichbannerFilterChecker.class);
        richbannerService = new RichbannerServiceImp(mapper, jsonTransformer, richbannerDaoImp, checker);

    }



    @Test
    public void testToRichbannerDTO() throws Exception {
        RichbannerDTO richbannerDTO = new RichbannerDTO();
        Richbanner richbanner = richbannerService.toRichbanner(richbannerDTO);
        assertNotNull(richbanner.getFilterData());
        SamplesUtils.fillRichbannerDTO(richbannerDTO);
        richbanner = richbannerService.toRichbanner(richbannerDTO);
        assertNotNull(richbanner.getFilterData());
    }

    @Test
    public void testToRichbanner() throws Exception {
        Richbanner richbanner = new Richbanner();
        richbanner.setFilterData("{}");
        richbanner.setId(1L);
        richbanner.setActive(true);
        RichbannerDTO richbannerDTO = richbannerService.toRichbannerDTO(richbanner);
        assertEquals(richbannerDTO.getFilter(), new RichbannerClientFilter());
    }

    @Test
    public void testSaveRichbanner() throws Exception {
        RichbannerDTO richbannerDTO = SamplesUtils.fillRichbannerDTO(new RichbannerDTO());
        Richbanner richbanner = richbannerService.toRichbanner(richbannerDTO);
        richbannerService.saveRichbanner(richbannerDTO);
        verify(richbannerDaoImp).save(richbanner);
    }




    @Test
    public void testIsAccountNumber() throws Exception {
        assertTrue("Unexpected result for isAccountNumber method", richbannerService.isAccountNumber("9002075726012"));
        assertFalse("Unexpected result for isAccountNumber method", richbannerService.isAccountNumber("F002075726012"));
        assertFalse("Unexpected result for isAccountNumber method", richbannerService.isAccountNumber(null));
        assertFalse("Unexpected result for isAccountNumber method", richbannerService.isAccountNumber(""));
        assertFalse("Unexpected result for isAccountNumber method", richbannerService.isAccountNumber("900207572601"));
        assertFalse("Unexpected result for isAccountNumber method", richbannerService.isAccountNumber("90020757260123"));
    }


    @Test
    public void testRemoveAllNotNumberChr() throws Exception {
        assertEquals("Unexpected result for removeAllNotNumberChr method","9002075726012", richbannerService.removeAllNotNumberChr("9002075726012"));
        assertEquals("Unexpected result for removeAllNotNumberChr method","9002075726012", richbannerService.removeAllNotNumberChr(".900.2075726012."));
        assertFalse("Unexpected result for removeAllNotNumberChr method","9002075726012".equals(richbannerService.removeAllNotNumberChr("900205726012")));
        assertEquals("Unexpected result for removeAllNotNumberChr method","9002075726012", richbannerService.removeAllNotNumberChr("п»ї9002075726012"));
        assertEquals("Unexpected result for removeAllNotNumberChr method","9002075726012", richbannerService.removeAllNotNumberChr("п»їdfdf9002075726012asdfasf"));
        assertEquals("Unexpected result for removeAllNotNumberChr method","9002075726012", richbannerService.removeAllNotNumberChr("п»їdfdf9002075sdfsdf726012asdfasf"));

    }
}
