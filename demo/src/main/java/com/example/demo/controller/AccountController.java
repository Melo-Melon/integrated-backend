package com.example.demo.controller;

import com.example.demo.Repository.AccountRepo;
//import com.example.demo.Repository.CodeaccRepo;
//import com.example.demo.Repository.MailaccRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.MailSendException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Account;
import com.example.demo.model.Check;
//import com.example.demo.model.Codeacc;
import com.example.demo.model.EditAccount;
//import com.example.demo.model.Email;
//import com.example.demo.model.ForgetAccount;
//import com.example.demo.model.Mailacc;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import java.util.Optional;
//import java.util.Random;

@RestController
public class AccountController {

   

    // Handle the account repository
    @Autowired
    private AccountRepo accountrepo;

    // Use to register(true and false)
    @PostMapping(value = "account")
    public Check CreateAccount(@RequestBody Account acc) {
        
        Check check = new Check();
        if (acc.getuser().isEmpty() || acc.getpass().isEmpty()) {
            //Regicheck.setCheck(false);
            check.setCheck(0);
            // return "You haven't entered account or password";
            return check;
        }

        Optional<Account> existacc = accountrepo.findById(acc.getuser());
        if (existacc.isPresent()) {
            check.setCheck(0);
            //Regicheck.setCheck(false);
            // return "This username already exists. Please try again.";
            return check;
        } else {
            //Regicheck.setCheck(true);
            check.setCheck(1);
            accountrepo.save(acc);
            // Welcome!";
            return check;
        }

    }

    // Use to login
    @PostMapping(value = "login")
    public Check Login(@RequestBody Account acc) {
        Check check = new Check();
        if (acc.getuser().isEmpty() || acc.getpass().isEmpty()) {
            //Logincheck.setCheck(false);
            check.setCheck(0);
            // return "Please enter the account or Password";
            return check;
        }
        Optional<Account> existacc = accountrepo.findById(acc.getuser());

        if (existacc.isPresent()) {
            Account matchacc = existacc.get();

            if (matchacc.getpass().equals(acc.getpass())) {
                //Logincheck.setCheck(true);
                check.setCheck(1);
                // return acc.getuser()+" "+acc.getpass();
                return check;
            } else {
                //Logincheck.setCheck(false);
                check.setCheck(0);
                // return "You've entered the wrong password! Please try again!";
                return check;
            }

        } else {
            //Logincheck.setCheck(false);
           check.setCheck(0);
            // return "Username is not registered. Please create a new account!";
            return check;
        }

    }

    // Use to edit password (return true and false)
    @PostMapping(value = "editpassword")
    public Check EditPassword(@RequestBody EditAccount acc) {
        Check check = new Check();
        if (acc.getuser().isEmpty() || acc.getorgpass().isEmpty()) {
           // Editcheck.setCheck(false);
           check.setCheck(0);
            return check;
        }
        Optional<Account> existacc = accountrepo.findById(acc.getuser());
        if (existacc.isPresent()) {
            Account matchacc = existacc.get();
            if (matchacc.getpass().equals(acc.getorgpass())) {
                accountrepo.deleteById(matchacc.getuser());
                Account newaccount = new Account(acc.getuser(), acc.getnewpass());
                accountrepo.save(newaccount);
                //Editcheck.setCheck(true);
                check.setCheck(1);
                return check;

            } else {
                //Editcheck.setCheck(false);
                check.setCheck(0);
                return check;
            }

        } else {
           // Editcheck.setCheck(false);
           check.setCheck(0);
            return check;
        }

    }



    //**********The code below is related to email verification***********/
    
    /*
    // Generate a HashMap to store verify code
    private Map<String, Object> resultMap = new HashMap<>();

    // Utilize to send mail
    @Autowired
    private JavaMailSender javaMailSender;

    // Get Sender
    @Value("${mail.fromMail.sender}")
    private String sender;

    // Send VerifyCode to Mail
    @PostMapping(value = "email")
    public String VerifyEmail(@RequestBody Email email) {
        if (email.getEmailaddress().isEmpty()) {
            return "No Email Address found";
        }
        SimpleMailMessage message = new SimpleMailMessage();
        String verifycode = VerifyCode(6);
        message.setFrom(sender);
        message.setTo(email.getEmailaddress());
        message.setSubject("Verification Mail");
        message.setText("[Verification For Simple Connect] Your verify code is: " + verifycode + ".");
        try {
            javaMailSender.send(message);
            savecode(verifycode);
            return checkcode();
            // return "Mail sent successfully";

        } catch (MailSendException e) {
            return "No Email Address Available" + e;
        } catch (Exception e) {
            return "Something wrong with the sending process" + e;
        }

    }

    // Used to create a random Verify code
    private String VerifyCode(int n) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
        // System.out.println(sb);
        return sb.toString();
    }

    // Save the verify code into a HashMap
    private void savecode(String code) {
        resultMap.put("Code", code);
    }

    // Check whether the code is the same
    private String checkcode() {

        System.out.println(resultMap.get("Code").toString());
        return resultMap.get("Code").toString();

    }

    //A specialized function to send verify code with email
    // This one can be used as a common sender function
    public String VerifyEmailwithmail(String email) {
        if (email.isEmpty()) {
            return "No Email Address found";
        }

        SimpleMailMessage message = new SimpleMailMessage();
        String verifycode = VerifyCode(6);
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("Verification Mail");
        message.setText("[Verification For Simple Connect] Your verify code is: " + verifycode + ".");
        try {
            javaMailSender.send(message);
            savecode(verifycode);
            return checkcode();
            // return "Mail sent successfully";

        } catch (MailSendException e) {
            return "No Email Address Available" + e;
        } catch (Exception e) {
            return "Something wrong with the sending process" + e;
        }

    }

    @GetMapping(value = "Code")
    public String GetCode() {
        return resultMap.get("Code").toString();
    }

    // *******Edit Regi part for using email***************
    @Autowired
    private MailaccRepo Mailaccrepo;

    @PostMapping(value = "Regi")
    public String CreateEmailAccount(@RequestBody Mailacc acc) {
        
        String code = VerifyEmailwithmail(acc.getEmail());

        if (!code.equals(resultMap.get("Code").toString())) {
            return "You verify code is wrong";
        }

        // Check check = new Check();
        if (acc.getUsername().isEmpty() || acc.getUsrpwd().isEmpty()) {
            // check.setCheck(false);
            return "You haven't entered account or password";
            // return check;
        }

         // 确保同一邮箱只有一个
        Optional<Mailacc> mailvery=Mailaccrepo.findById(acc.getEmail());
        if(mailvery.isPresent()){
            return "This email address has already been registered";
        }

        
        //Optional<Mailacc> existacc = Mailaccrepo.findById(acc.getUsername());
        List<Mailacc> existacc = Mailaccrepo.findAll();
        
        // This part needs to add the verification of the code given to the user.
       for(int i=0;i<existacc.size();i++){
           if(existacc.get(i).getUsername().equals(acc.getUsername())){
                return "Username have already been registerd";
           }
       }
            // check.setCheck(true);
        return Mailaccrepo.save(acc).getUsername() + " is registered Successfully! Welcome!";
            // return check;

        

    }


    @Autowired
    private CodeaccRepo Codeaccrepo;
    @PostMapping(value = "codeacc")
    public String CreateCodeAccount(@RequestBody Codeacc acc) {
        

        if (!acc.getCode().equals(resultMap.get("Code").toString())) {
            return "You verify code is wrong";
        }

        // Check check = new Check();
        if (acc.getUsername().isEmpty() || acc.getUsrpwd().isEmpty()) {
            // check.setCheck(false);
            return "You haven't entered account or password";
            // return check;
        }

         // 确保同一邮箱只有一个
        Optional<Codeacc> mailvery=Codeaccrepo.findById(acc.getEmail());
        if(mailvery.isPresent()){
            return "This email address has already been registered";
        }

        
        //Optional<Mailacc> existacc = Mailaccrepo.findById(acc.getUsername());
        List<Codeacc> existacc = Codeaccrepo.findAll();
        
        // This part needs to add the verification of the code given to the user.
       for(int i=0;i<existacc.size();i++){
           if(existacc.get(i).getUsername().equals(acc.getUsername())){
                return "Username have already been registerd";
           }
       }
            // check.setCheck(true);
        return Codeaccrepo.save(acc).getUsername() + " is registered Successfully! Welcome!";
            // return check;

        

    }




    @PostMapping(value="forget")
    public String MatchForgetPassword(@RequestBody ForgetAccount forget){
        if (!forget.getCode().equals(resultMap.get("Code").toString())) {
            return "You verify code is wrong";
        }

        if(forget.getEmailaddress().isEmpty()){
            return "Plz enter your emailaddress to find back your password";
        }
        Optional<Mailacc> existmailacc=Mailaccrepo.findById(forget.getEmailaddress());

        if(existmailacc.isPresent()){
            return "Your password is "+ existmailacc.get().getUsrpwd();
        }else{
            return "No account for your email address";
        }
        
        //send email
        if(forget.getCode()==resultMap.get("Code")){
            Optional<Mailacc> existmailacc=Mailaccrepo.findById(forget.getEmailaddress());
            if(existmailacc.isPresent()){
                return "Your password is "+ existmailacc.get().getUsrpwd();
            }else{
                return "No account for your email address";
            }
        }else{
            return "Not correct Verify code";
        }

        
    }


    @PostMapping(value="forgetcode")
    public String MatchForgetCodePassword(@RequestBody ForgetAccount forget){
        if (!forget.getCode().equals(resultMap.get("Code").toString())) {
            return "You verify code is wrong";
        }

        if(forget.getEmailaddress().isEmpty()){
            return "Plz enter your emailaddress to find back your password";
        }

        Optional<Codeacc> existmailacc=Codeaccrepo.findById(forget.getEmailaddress());

        if(existmailacc.isPresent()){
            return "Your password is "+ existmailacc.get().getUsrpwd();
        }else{
            return "No account for your email address";
        }
        
        //send email
        if(forget.getCode()==resultMap.get("Code")){
            Optional<Mailacc> existmailacc=Mailaccrepo.findById(forget.getEmailaddress());
            if(existmailacc.isPresent()){
                return "Your password is "+ existmailacc.get().getUsrpwd();
            }else{
                return "No account for your email address";
            }
        }else{
            return "Not correct Verify code";
        }

        
    }
    */


}
