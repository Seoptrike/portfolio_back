package com.backend.service.users;

import com.backend.domain.user.UsersVO;
import java.util.HashMap;
import java.util.List;

public interface UserService {
  List<UsersVO> getUserAllList();

  int findUserID(String username);

  HashMap<String, Object> getUserTotalData(String username);

  List<UsersVO> searchUsername(String username);

  void updateUserData(UsersVO vo);

  void softDeleteUser(String username);

  UsersVO getUserData(String username);
}
