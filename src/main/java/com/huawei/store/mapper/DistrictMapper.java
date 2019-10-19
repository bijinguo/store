package com.huawei.store.mapper;

import com.huawei.store.entity.Address;
import com.huawei.store.entity.District;

import java.util.HashSet;
import java.util.List;

public interface DistrictMapper {
   List<District> findByParent(String parent);
   District findByCode(String code);



}
