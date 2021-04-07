var express = require('express');
var router = express.Router();
var bcryptjs = require('bcryptjs');
var User = require('../model/User');
const { response } = require('../app');


/*đăng kí*/
router.post('/dangky', function(req, res, next) {
  try{
    var post_data = req.body;  
    var name = post_data.name;
    var email = post_data.email;
    var phonenumber =post_data.phonenumber;
    var InfoUser = post_data.InfoUser;
    var address = post_data.address 
    if (!email  || !req.body.password )
    {
       return res
       .status(400)
       .json({success:false,message:"Username or password not empty"})
    }
    else if(!name)
    {
      return res
      .status(400)
      .json({success:false,message:"Name not empty"})
    }
    else if(!phonenumber)
    {
      return res
      .status(400)
      .json({success:false,message:"Phonenumber not empty"})
    }
    else 
    {
      var hashPassword = bcryptjs.hashSync(req.body.password, 10);
    var innsertUser = {
      '_email' : email,
      '_password' : hashPassword,
      '_name':name,
      '_InfoUser':InfoUser,
      '_PhoneNumber':phonenumber,
      '_Address':address
    }
    User.findOne({'_email':email}).count(function(err,number){
      if(number != 0)
      {
        res.send('Email already exists');
      }
      else {
        var dulieu = new User(innsertUser);
        dulieu.save();
        res.send('Register success');
      }
    
    
    })
  }
  }catch (err) {
    res.status(500).json({
        success: false,
        message: err.message
    });
}
});

//đăng nhập
router.post('/dangnhap', function(req, res, next) {
  try {
    var post_data = req.body;
    var email = post_data.email;
    User.findOne({'_email':email}).count(function(err,number){
      if(number == 0)
      {
        res.json('Email not exists');
      }
      else {
        User.findOne({'_email':email},function(error,data){
          
          if(!bcryptjs.compareSync(req.body.password, data._password))
          {
            res.json('Wrong password or email');
           
          }
          else{
            res.json('Login success');
          }
        })
      }
    })


  } catch (err) {
    res.status(500).json({
      success: false,
      message: err.message
  });
  }
});

module.exports = router;
