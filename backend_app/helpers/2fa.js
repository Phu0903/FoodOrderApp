const otplib = require('otplib');



module.exports = {
  generateUniqueSecret : () => {
    return otplib.totp.generateSecret();
  },

  /** Tạo mã OTP token */
   
  generateOTPToken : (username, mailclient)=> {
    const token = otplib.totp.generate(username, mailclient);
    return token;
  },
  /** Kiểm tra mã OTP token có hợp lệ hay không
   * Có 2 method "verify" hoặc "check", các bạn có thể thử dùng một trong 2 tùy thích.
  */
  verifyOTPToken : (token, secret) => {
    return otplib.totp.verify({ token, secret })
    // return authenticator.check(token, secret)
  },
}




