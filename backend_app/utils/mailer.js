const nodeMailer = require('nodemailer')
const OTP = require('../helpers/2fa');




// Những thông tin dưới đây các bạn có thể ném nó vào biến môi trường env nhé.
// Vì để demo nên mình để các biến const ở đây.
const adminEmail = 'phucotrithihamhoc@gmail.com'
const adminPassword = 'baflhzzxihredlqp'
// Mình sử dụng host của google - gmail
const mailHost = 'smtp.gmail.com'
// 587 là một cổng tiêu chuẩn và phổ biến trong giao thức SMTP
const mailPort = 587
const sendMail = (mailclient, orderID,address,phonenumber,name,total,product) => {
  // Khởi tạo một thằng transporter object sử dụng chuẩn giao thức truyền tải SMTP với các thông tin cấu hình ở trên.
  const transporter = nodeMailer.createTransport({
    host: mailHost,
    port: mailPort,
    secure: false, // nếu các bạn dùng port 465 (smtps) thì để true, còn lại hãy để false cho tất cả các port khác
    auth: {
      user: adminEmail,
      pass: adminPassword
    }
  })
  /*const otp = OTP.generateOTPToken(mailclient,mailclient);
  const options = {
    from: 'phucotrithihamhoc@gmail.com'
    , // địa chỉ admin email bạn dùng để gửi
    to: mailclient
    , // địa chỉ gửi đến
    subject: 'test mail', // Tiêu đề của mail
    html:'Cảm ơn bạn đã đăng ký. Mã OTP của bạn '+ otp// Phần nội dung mail mình sẽ dùng html thay vì thuần văn bản thông thường.
  }*/
  const options = {
    from: 'phucotrithihamhoc@gmail.com'
    , // địa chỉ admin email bạn dùng để gửi
    to: mailclient
    , // địa chỉ gửi đến
    subject: 'Đặt hàng thành công. Mã đơn hàng: ' + orderID, // Tiêu đề của mail
    html:'Bạn đã đặt hàng thành công <br> Tên: ' + name +'<br> Số điện thoại: 0' + phonenumber 
         +'<br> Địa chỉ: ' +address
         +'<br> Tổng: ' + total+'$'
         
          
    
    
    // Phần nội dung mail mình sẽ dùng html thay vì thuần văn bản thông thường.
  }
  // hàm transporter.sendMail() này sẽ trả về cho chúng ta một Promise
  return transporter.sendMail(options)
}
/*const postVerify2FA = async (req, res) => {
  try {
   
    const { otpToken,mailclient } = req.body
    
    // Kiểm tra mã token người dùng truyền lên có hợp lệ hay không?
    const isValid = OTP.verifyOTPToken(otpToken,mailclient)
    /** Sau bước này nếu verify thành công thì thực tế chúng ta sẽ redirect qua trang đăng nhập thành công,
    còn hiện tại demo thì mình sẽ trả về client là đã verify success hoặc fail 
    return res.status(200).json({ isValid })
  } catch (error) {
    console.log(error)
    return res.status(500).json(error)
  }
}*/
module.exports = {
  sendMail,
  //postVerify2FA
}