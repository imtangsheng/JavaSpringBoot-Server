package com.robot.RobotServer.db.mysql;

import org.springframework.data.repository.CrudRepository;
import com.robot.RobotServer.db.mysql.User;
public interface UserRepository extends CrudRepository<User,Integer> {
}
