package com.moneytree.moneywhatsapp.entitymodels;

import com.moneytree.moneywhatsapp.enums.ConfigEnums;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class TblConfiguration extends TblMetaData {
    
    @Id
    @Enumerated(value = EnumType.STRING)
    private ConfigEnums configKey;

    private String configValue;

    public TblConfiguration() {}

    public ConfigEnums getConfigKey() {
        return configKey;
    }

    public void setConfigKey(ConfigEnums configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
