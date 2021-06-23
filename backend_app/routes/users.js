var express = require('express');
var userRouter = express.Router();
var bcryptjs = require('bcryptjs');
var User = require('../model/User');
const { response } = require('../app');
const mailer = require('../utils/mailer')
const otplib = require('../helpers/2fa');

userRouter.post('/CheckUser', async (req, res) => {
  const {
    email,
    phonenumber
  } = req.body
  try {
    const number1 = await User.find({ '_email': email }).count()
    const number2 = await User.find({ '_PhoneNumber': phonenumber }).count();
    //const number = await User.findOne({'_email':email,'_PhoneNumber':phonenumber}).count()

    if (number1 == 0 && number2 == 0) {
      res.json({
        message: "U can register"
      })
    }
    else {
      res.json({
        message: "email or phone already exists"
      })
    }
  } catch (error) {
    res.status(500).json({
      success: false,
      message: error.message
    });
  }
})


/*đăng kí*/
userRouter.post('/dangky', async (req, res, next) => {
  try {
    var post_data = req.body;
    var name = post_data.name;
    var email = post_data.email;
    var phonenumber = post_data.phonenumber;
    var address = post_data.address
    if (!email || !req.body.password) {
      return res
        .json({ success: false, "message": "Username or password not empty" })
    }
    else if (!name) {
      return res
        .status(200)
        .json({ success: false, "message": "Name not empty" })
    }
    else if (!phonenumber) {
      return res
        .status(200)
        .json({ success: false, "message": "Phonenumber not empty" })
    }
    else {
      var hashPassword = bcryptjs.hashSync(req.body.password, 10);
      var innsertUser = {
        '_email': email,
        '_password': hashPassword,
        '_name': name,
        '_PhoneNumber': phonenumber,
        '_Address': address
      }
      var dulieu = new User(innsertUser);
      dulieu.save();
      res.status(200).json({ "message": 'Register success' });


    }
  } catch (err) {
    res.status(500).json({
      success: false,
      message: err.message
    });
  }
});

//đăng nhập
userRouter.post('/dangnhap', async (req, res, next) => {
  try {
    var post_data = req.body;
    var email = post_data.email;
    User.findOne({ '_email': email }).count(function (err, number) {
      if (number == 0) {

        res.json({ "message": 'Wrong email or password' });
      }
      else {
        User.findOne({ '_email': email }, function (error, data) {

          if (!bcryptjs.compareSync(req.body.password, data._password)) {
            res.json({ "message": 'Wrong email or password' });
          }
          else {
            res.status(201).json({
              "message": 'Login success'
            });
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

userRouter.put('/UpdateUser', async (req, res) => {
  try {
    const { email, name, address, urlimage, PasswordReset } = req.body;
    const resetPassword = await argon2d.hash(PasswordReset)//hasd password by argon 
    const AccountUser = await User.findOne({ '_email': email })
    if (!AccountUser) {
      res.json('No have account')
    }
    else {
      
      User.updateOne({ _email: email },
        {
          $set: {
            '_name': name || AccountUser._name,
            '_Address':address||AccountUser._Address,
            '_password':resetPassword||AccountUser._password,
            
          }
        }, function (error, data) {
          res.json("Oke")
        })
      }
    
  
  } catch (error) {
    res.status(500).json({
      success: false,
      message: err.message
    });
}
})


//xác minh mail
userRouter.post('/send-email', async (req, res) => {
  try {
    const { emailclient } = req.body

    // Thực hiện gửi email
    await mailer.sendMail(emailclient, emailclient)

    // Quá trình gửi email thành công thì gửi về thông báo success cho người dùng
    res.send('oke')
  } catch (error) {
    res.status(500).json({
      success: false,
      message: error.message
    });
  }
})
//vertifileOTP
userRouter.post('/verifileOTP', mailer.postVerify2FA)

//getInforUser
userRouter.get('/inforUser', async (req, res) => {
  try {
    var email = req.query.email;
    User.findOne({ '_email': email }, function (err, dulieu) {
      res.status(200).json(dulieu);
    })
  } catch (err) {
    res.status(500).json({
      success: false,
      message: err.message
    });
  }
})
//mail


module.exports = userRouter;
