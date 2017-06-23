package com.zingcrm.dao;

import java.util.List;

import com.zingcrm.entity.State;
import com.zingcrm.exception.BusinessException;


public interface StateDAO {

	List<State> readStateCode(String countryId) throws BusinessException;

}
