package Design.CampusConnect.service;

import Design.CampusConnect.Error.UserAlreadyExistException;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.Pojos.UserDto;

public interface IUserService {
    Student registerNewUserAccount(UserDto accountDto)
            throws UserAlreadyExistException;
}