package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.City;
import com.zingcrm.exception.BusinessException;

public interface CityDAO {


	List<City> readCityCode(String stateId)
			throws BusinessException;




}
