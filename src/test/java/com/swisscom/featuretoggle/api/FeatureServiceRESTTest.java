package com.swisscom.featuretoggle.api;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.swisscom.featuretoggle.model.FeatureVO;
import com.swisscom.featuretoggle.model.PagingInfo;
import com.swisscom.featuretoggle.service.FeatureService;
import com.swisscom.featuretoggle.util.FeatureBuilder;

@SpringBootTest()
class FeatureServiceRESTTest {

	
	private static final Long DEFAULT_ID = 1L;
	@Mock
	private FeatureService service;

	@InjectMocks
	private FeatureServiceREST featureService;
	
	private PagingInfo info;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		info = new PagingInfo(1,10);
	}

	@DisplayName("Validate feature find by id - Success")
	@Test
	void test_find_success() {
		Long id = Double.valueOf(Math.random()).longValue();
		FeatureVO mockedFeature = new FeatureBuilder().id(id).technicalName("test-name").buildVO();
		when(service.find(id)).thenReturn(mockedFeature);

		
		Response response = featureService.find(id);
		FeatureVO searchedFeature = (FeatureVO) response.getEntity();
		Assertions.assertEquals(response.getStatus(), 200, "Feature not found");
		Assertions.assertEquals(searchedFeature.getId(), mockedFeature.getId(), "Feature not found");
	}

	@DisplayName("Validate feature find by id - Fail")
	@Test
	void test_find_fail() {
		when(service.find(Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);

		Response response = featureService.find(DEFAULT_ID);
		
		Assertions.assertEquals(response.getStatus(), 404, "Wrong error status");
	}

	@DisplayName("Validate feature list - Success")
	@Test
	void test_list_success() {
		List<FeatureVO> mockedList = new ArrayList<>();
		Long id = Double.valueOf(Math.random()).longValue();
		mockedList.add(new FeatureBuilder().id(id).technicalName("test-name").buildVO());
		mockedList.add(new FeatureBuilder().id(id + 1).technicalName("another-test-name").buildVO());

		when(service.list(DEFAULT_ID,info)).thenReturn(new PageImpl<FeatureVO>(mockedList));

		Response response = featureService.list(DEFAULT_ID,info);
		Page<FeatureVO> searchedList = (Page<FeatureVO>) response.getEntity();
		
		Assertions.assertEquals(response.getStatus(), 200, "Feature list not found");
		Assertions.assertNotNull(searchedList);
		Assertions.assertEquals(searchedList.getNumberOfElements(), 2, "Too few elements found");
	}

	@DisplayName("Validate feature save - Success")
	@Test
	void test_save_success() {
		Long id = Double.valueOf(Math.random()).longValue();
		FeatureBuilder builder = new FeatureBuilder().id(id);
		FeatureVO mockedFeature = builder.buildVO();
		when(service.save(Mockito.any(FeatureVO.class))).thenReturn(mockedFeature);
		
		Response response = featureService.save(builder.buildVO());
		FeatureVO savedFeature = (FeatureVO) response.getEntity();
		Assertions.assertEquals(response.getStatus(), 200, "Feature not saved");
		Assertions.assertEquals(savedFeature.getId(), mockedFeature.getId(), "Feature not saved");
	}

	@DisplayName("Validate feature save - Fail")
	@Test
	void test_save_fail() {
		Long id = Double.valueOf(Math.random()).longValue();
		FeatureBuilder builder = new FeatureBuilder().id(id).technicalName("test-name");
		when(service.save(Mockito.any(FeatureVO.class))).thenThrow(IllegalArgumentException.class);

		Response response = featureService.save(builder.buildVO());
		Assertions.assertEquals(response.getStatus(), 400, "Wrong error status");
	}

}
