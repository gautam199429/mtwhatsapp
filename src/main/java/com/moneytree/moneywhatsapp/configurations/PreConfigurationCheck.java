package com.moneytree.moneywhatsapp.configurations;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import com.moneytree.moneywhatsapp.enums.ConfigEnums;
import com.moneytree.moneywhatsapp.repository.TblConfigurationRepo;

@Configuration
public class PreConfigurationCheck implements ApplicationRunner{

    private final TblConfigurationRepo tblConfigurationRepo;

    public PreConfigurationCheck(TblConfigurationRepo tblConfigurationRepo) {
        this.tblConfigurationRepo = tblConfigurationRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ConfigEnums> allProperties = Arrays.stream(ConfigEnums.values()).toList();
        for(ConfigEnums properties : allProperties){
            if(tblConfigurationRepo.findByConfigKey(properties) != null){
                System.out.println("Property set for the value: "+properties.name());
            }
            else{
                System.out.println("Missing required config: "+properties.name());
                throw new IllegalStateException("Missing required config: " + properties.name());
            }
        }
    }

}
