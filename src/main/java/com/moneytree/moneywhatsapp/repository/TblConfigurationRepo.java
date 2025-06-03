package com.moneytree.moneywhatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.moneytree.moneywhatsapp.entitymodels.TblConfiguration;
import com.moneytree.moneywhatsapp.enums.ConfigEnums;

@Repository
public interface TblConfigurationRepo extends JpaRepository<TblConfiguration, ConfigEnums> {

    TblConfiguration findByConfigKey(ConfigEnums configKey);

}
