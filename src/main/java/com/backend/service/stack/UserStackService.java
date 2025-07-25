package com.backend.service.stack;

import com.backend.domain.UserStackRequestDTO;
import com.backend.domain.UserStacksVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserStackService {
    List<UserStacksVO> findStackByUserID(int userID);

    void insertUserStack(UserStackRequestDTO dto);

    void updateUserStack(UserStackRequestDTO dto);
}
