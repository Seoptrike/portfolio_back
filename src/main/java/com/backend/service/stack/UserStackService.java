package com.backend.service.stack;

import com.backend.domain.stack.UserStackRequestDTO;
import com.backend.domain.stack.UserStacksVO;
import java.util.HashMap;
import java.util.List;

public interface UserStackService {
  List<UserStacksVO> findStackByUserID(int userID);

  void insertUserStack(UserStackRequestDTO dto);

  void updateUserStack(UserStackRequestDTO dto);

  List<HashMap<String, Object>> selectUserStackByUserId(String username);
}
