package com.swisscom.featuretoggle.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.data.domain.PageRequest;

import com.swisscom.featuretoggle.model.Feature;
import com.swisscom.featuretoggle.model.FeatureVO;
import com.swisscom.featuretoggle.model.PagingInfo;
import com.swisscom.featuretoggle.repository.FeatureRepository;
import com.swisscom.featuretoggle.util.FeatureBuilder;

@SpringBootTest()
class FeatureServiceTest {
	
	private static final Long DEFAULT_ID = 1L;

	@Mock
	private FeatureRepository repository;

	@InjectMocks
	private FeatureService featureService;
	
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
		Feature mockedFeature = new FeatureBuilder().id(id).technicalName("test-name").build();
		when(repository.findById(id)).thenReturn(Optional.of(mockedFeature));

		FeatureVO searchedFeature = featureService.find(id);
		Assertions.assertEquals(searchedFeature.getId(), mockedFeature.getId(), "Feature not found");
	}

	@DisplayName("Validate feature find by id - Fail")
	@Test
	void test_find_fail() {
		when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			featureService.find(1L);
		}, "Wrong Exception thrown");
	}

	@DisplayName("Validate feature list - Success")
	@Test
	void test_list_success() {
		List<Feature> mockedList = new ArrayList<Feature>();
		Long id = Double.valueOf(Math.random()).longValue();
		mockedList.add(new FeatureBuilder().id(id).technicalName("test-name").build());
		mockedList.add(new FeatureBuilder().id(id + 1).technicalName("another-test-name").build());

		when(repository.findByCustomerId(DEFAULT_ID,info.toPageable())).thenReturn(new PageImpl<Feature>(mockedList));

		Page<FeatureVO> searchedList = featureService.list(DEFAULT_ID,info);
		Assertions.assertNotNull(searchedList);
		Assertions.assertEquals(searchedList.getNumberOfElements(), 2, "Too few elements found");
	}

	@DisplayName("Validate feature save - Success")
	@Test
	void test_save_success() {
		Long id = Double.valueOf(Math.random()).longValue();
		FeatureBuilder builder = new FeatureBuilder().id(id);
		Feature mockedFeature = builder.build();
		when(repository.save(Mockito.any(Feature.class))).thenReturn(mockedFeature);

		FeatureVO savedFeature = featureService.save(builder.buildVO());
		Assertions.assertEquals(savedFeature.getId(), mockedFeature.getId(), "Feature not saved");
	}

	@DisplayName("Validate feature save - Fail")
	@Test
	void test_save_fail() {
		Long id = Double.valueOf(Math.random()).longValue();
		FeatureBuilder builder = new FeatureBuilder().id(id).technicalName("test-name");
		when(repository.save(Mockito.any(Feature.class))).thenThrow(IllegalArgumentException.class);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			featureService.save(builder.buildVO());
		}, "Wrong Exception thrown");
	}

}
