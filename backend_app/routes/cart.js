var express = require('express');
var cartRouter = express.Router();
var cart = require('../model/Cart');
var User = require('../model/User');
var ProductFood = require('../model/ProductFood');
const { json } = require('express');



//Hàm thêm vào giỏ hàng
cartRouter.post('/addTocart', async (req, res) => {
    try {
        var post_data = req.body; //Lấy dữ liệu từ clietn
        var ProductID = post_data.ProductID;
        var email = post_data.email;
        var quantity = Number(post_data.quantity);//chuyển đổi quantity sang number
        cart.find({
            '_email': email,
            '_ProductID': ProductID,
        }).count(function (err, number) {//kiểm tra xem đã có hàng trong giỏ hàng chưa
            if (number != 0) {//nếu có
                cart.findOne({
                    '_email': email,
                    '_ProductID': ProductID,
                }, function (err, data) {
                    var quantity_temp = (Number(data._quantity) + quantity);//tăng số lương hàng trong giỏ hàng
                    cart.findOneAndUpdate({
                        '_email': email,
                        '_ProductID': ProductID,
                        '_quantity': data._quantity,
                    },
                        {
                            '_quantity': String(quantity_temp)
                        },
                        {
                            new: true // trả vè dữ liệu mới 
                            //Hàm này trả về defaut là dữ liệu cũ

                        }, function (err, data) {
                        if (err) { res.status(500).json(err) }
                        else { res.status(201).json("Thêm vào thành công") }
                    })

                })
            }
            else {//nếu ko có phần tử thì thêm mới 
                var dataCartNew = {
                    '_email': email,
                    '_ProductID': ProductID,
                    '_quantity': quantity,
                }
                var dataNewCart = new cart(dataCartNew);
                dataNewCart.save(function (err) {
                    res.status(201).json("Thêm vào thành công")
                });


            }
        })

    } catch (err) {
        res.status(500).json({
            success: false,
            message: err.message
        });
    }
})
//delete cart
cartRouter.delete('/delete', async (req, res) => {
    try {
        var post_data = req.body; //Lấy dữ liệu từ client
        var ProductID = post_data.ProductID;
        var email = post_data.email;
        cart.remove({
            '_email': email,
            '_ProductID': ProductID
        }, function (err) {
            res.status(201).json({ success: true });
        })

    } catch (err) {
        res.status(500).json({
            success: false,
            message: err.message
        });
    }
})
cartRouter.get('/getCart', async (req, res) => {
    try {
        cart.findOne({'_email':'phu123456789'}, function (err, dulieu) {
       res.status(201).json(dulieu);
       //res.render('ViewProduct', { title: 'xem dữ liệu', products: dulieu })
     })
    } catch (err) {
     res.status(500).json({
       success: false,
       message: err.message
   });
    }
})
cartRouter.post('/getEamil',async(req,res)=>{
 
    var  post_data = req.body; 
    var email = post_data.email;
        cart.aggregate([
            {"$match": { "_email": email }},
        {$lookup:{
            from:'users',
            localField:'_email',
            foreignField:'_email',
            as:'user',
         
    }}
],function (err,data) {
        if(err) throw err;
          res.send(data);
    });
});
module.exports = cartRouter;

