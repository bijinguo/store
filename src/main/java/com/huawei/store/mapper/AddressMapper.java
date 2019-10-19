package com.huawei.store.mapper;

import com.huawei.store.entity.Address;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.List;

public interface AddressMapper {

    Integer getCountByUid(Integer uid);

    Integer saveAddress(Address address);
    List<Address> findByUid(Integer uid);
}
