package ru.fls.privateoffice.util.service.richbanner;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import ru.fls.privateoffice.dao.impl.RichbannerDaoImp;
import ru.fls.privateoffice.util.dto.banner.RichbannerDTO;
import ru.rzd.loyalty.shared.json.JsonTransformer;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author YMolodkov
 * @since 16.0
 */
public class RichbannerEngineImpTest {

    private RichbannerServiceImp richbannerService;
    private RichbannerDaoImp richbannerDaoImp;
    private RichbannerEngine richbannerEngine;

    @Before
    public void setUp() throws Exception {
        Mapper mapper = new DozerBeanMapper();
        JsonTransformer jsonTransformer = new JsonTransformer();
        richbannerDaoImp = mock(RichbannerDaoImp.class);
        RichbannerFilterChecker checker = mock(RichbannerFilterChecker.class);
        richbannerService = new RichbannerServiceImp(mapper, jsonTransformer, richbannerDaoImp, checker);
        richbannerEngine = new RichbannerEngineImp(richbannerDaoImp, checker, richbannerService);

    }


    @Test
    public void testGetAllRichbanners() throws Exception {

    }

    @Test
    public void testGetRichBannerViewSet() throws Exception {

    }

//    @Test
//    public void testRichbannerList() throws Exception {
//        RichbannerDTO richbannerDTO = SamplesUtils.fillRichbannerDTO(new RichbannerDTO());
//        when(richbannerDaoImp.getAllRichbanners()).thenReturn(Arrays.asList(richbannerService.toRichbanner(richbannerDTO)));
//        List<RichbannerDTO> richbannerDTOs = richbannerEngine.richbannerList(new RichbannerAllFilter());
//        assertEquals(richbannerDTO.getFilter().getAccountNumber(),richbannerDTOs.iterator().next().getFilter().getAccountNumber());
//    }


}
