package zut.software.evaluation.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zut.software.evaluation.Entity.User;
import zut.software.evaluation.Service.UserService;
import zut.software.evaluation.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
