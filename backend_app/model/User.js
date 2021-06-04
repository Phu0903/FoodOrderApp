var mongoose = require('mongoose');
var users = new mongoose.Schema({
    _email:
     {
         type:String,
         required:true,
         unique:true
     },
    _password:
    {
        type:String,
        required:true
    },
    _name:
    {
        type:String,
        required:true
    },
 
    _PhoneNumber:
    {
          type:Number,
          required:true,
    },
    _Address:
    {
        type:String,
        required:true,
    } 
}, { collection: 'users' });

module.exports = mongoose.model('users', users)