package com.moneytree.moneywhatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.moneytree.moneywhatsapp.entitymodels.TblSentMessages;

@Repository
public interface TblSentMessagesRepo extends JpaRepository<TblSentMessages, Long> {


}
