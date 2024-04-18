package zut.software.evaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import zut.software.evaluation.Entity.User;
import zut.software.evaluation.Service.UserService;
import zut.software.evaluation.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluation/user")
public class Usercontroller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    /*@ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "/save")
    public User createUser(@RequestBody User user) {
        user.setId("55");
        user.setPassword("22222");
        user.setName("李");
        user.setAge(18);
        user.setSex("男");
        return userRepository.save(user);
    }*/
    /*
    * 关于登陆：根据id在数据库中查找用户，若用户不为空且账号密码都相等，则判定登陆成功
    *
    * */
    @GetMapping(value = "/login/{id}/{password}")
    public String login(@PathVariable("id") String id, @PathVariable("password") String password) {

        Optional<User> user = userRepository.findById(id);
                if(user.isPresent()){
                    if (user != null && user.get().getId().equals(id) && user.get().getPassword().equals(password)) {
                        return "success";
                    } else{
                        return "error";
                    }

                }else
                {
                    return "error";
                }
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping(value="/findbyid/{id}")
    public Optional<User> readUserById(@PathVariable("id") String id){
        return userRepository.findById(id);
    }

    /**
     * 根据一个或者多个属性查询单个结果
     * @param name
     * @return
     */
    @GetMapping(value="/findbyname/{name}")
    public Optional<User> readUserByName(@PathVariable("name") String name){
        User user = new User();
        user.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age","telephone");
        Example<User> example = Example.of(user, matcher);
        return userRepository.findOne(example);
    }

    /**
     * 根据一个或者多个属性分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/findalluser/{page}/{size}")
    public Page<User> readUsersByPage(@PathVariable("page") Integer page,
                                      @PathVariable Integer size) {
        Pageable pageable=PageRequest.of(page-1,size);
        return userRepository.findAll(pageable);
    }
    @GetMapping(value = "/findalluser")
    public List<User> Findall(){
        List<User> user=userRepository.findAll();
        return  user;
    }


    /**
     * 添加用户
     * */
    @PostMapping(value = "/saveuser")
    @ResponseBody
    public String saveUser(@RequestBody User user){
        Optional<User> test=userRepository.findById(user.getId());
        if(test.isPresent())
            return "chongfu";
        User result=userRepository.insert(user);
        if(result!=null)
            return "success";
        else
            return "error";
    }

    /**
     * 修改用户
     * */
    @PutMapping("/updateuser")
    @ResponseBody
    public String updateUser(@RequestBody User user){
        User result=userRepository.save(user);
        if(result!=null)
            return "success";
        else
            return "error";
    }

    /**
     * 删除用户
     * */
    @DeleteMapping ("/deletebyid/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") String id){
        userRepository.deleteById(id);
    }


}
