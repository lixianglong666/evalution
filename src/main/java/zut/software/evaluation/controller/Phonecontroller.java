package zut.software.evaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import zut.software.evaluation.Entity.Phone;
import zut.software.evaluation.Entity.Review;
import zut.software.evaluation.Entity.User;
import zut.software.evaluation.repository.PhoneRepository;
import zut.software.evaluation.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluation/phone")
public class Phonecontroller {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    /*
    * 根据手机号查找手机
    * */
    @GetMapping(value = "/findbyid/{phoneid}")
    public Optional<Phone> findbyid(@PathVariable("phoneid") String phoneid){
        return phoneRepository.findById(phoneid);
    }
    @GetMapping(value = "/findallphone/{page}/{size}")
    public Page<Phone> readPhonesByPage(@PathVariable("page") Integer page,
                                      @PathVariable Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return phoneRepository.findAll(pageable);
    }
    @GetMapping(value = "/findallphone")
    public List<Phone> Findall(){
        List<Phone> phones=phoneRepository.findAll();
        return  phones;
    }





    /**
     * 添加
     * */
    @PostMapping(value = "/savephone")
    @ResponseBody
    public String savePhone(@RequestBody Phone phone){
//        List<Review> reviewlist=reviewRepository.findByPhoneid(phone.getPhoneid());
//        int esum=0;
//        for (int i=0;i<reviewlist.size();i++)
//        {
//            esum+=reviewlist.get(i).getEmotion();
//        }
//        double zhvalue=Double.valueOf(esum)+Double.valueOf(phone.getSalenum())/100;
        phone.setZhvalue(0);
        phone.setSalenum("0");
       Optional<Phone> test= phoneRepository.findById(phone.getPhoneid());
        if(test.isPresent())
            return "chongfu";
        Phone result=phoneRepository.insert(phone);
        if(result!=null)
            return "success";
        else
            return "error";
    }

    /**
     * 修改
     * */
    @PutMapping("/updatephone")
    @ResponseBody
    public String updatePhone(@RequestBody Phone phone){
        Phone result=phoneRepository.save(phone);
        if(result!=null)
            return "success";
        else
            return "error";
    }

    /**
     * 删除
     * */
    @DeleteMapping ("/deletebyid/{id}")
    @ResponseBody
    public void deletePhone(@PathVariable("id") String id){
        phoneRepository.deleteById(id);
    }

}
