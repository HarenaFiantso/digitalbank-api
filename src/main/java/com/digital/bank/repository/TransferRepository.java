package com.digital.bank.repository;

import com.digital.bank.model.Transfer;
import com.digital.bank.util.drr.utility.DreamReflectRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TransferRepository extends DreamReflectRepository<Transfer> {
    public TransferRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected Transfer mapResultSet(ResultSet resultSet) {
        try{
            return Transfer.builder()
                    .idTransfer(resultSet.getString("id_transfer"))
                    .idTransferGroup(resultSet.getString("id_transfer_group"))
                    .build();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
