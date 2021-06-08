var express = require('express');
var cartRouter = express.Router();
var cart = require('../model/Cart');
var User = require('../model/User');
var ProductFood = require('../model/ProductFood');
const { json } = require('express');
const { findById } = require('../model/User');



//Hàm thêm vào giỏ hàng
cartRouter.post('/addTocart', async (req, res) => {
    try {
        var post_data = req.body; //Lấy dữ liệu từ client
        var ProductID = post_data.ProductID;
        var email = post_data.email;
        var quantity = Number(post_data.quantity);//chuyển đổi quantity sang number
     
        const productfood = await ProductFood.findOne({ '_ProductID': ProductID });
       
        if (!ProductID || !email || !quantity) {
            res.json({
                success: false,
                message: "false"

            })
        }
        else {
        
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
                                else {
                                    res.status(201).json({
                                        "success": "true",
                                        message: "Bỏ vào giỏ thành công"
                                    })
                                }
                            })

                    })
                }
                else {//nếu ko có phần tử thì thêm mới 
                    
                    const dataCartNew = {

                        '_ProductID': ProductID,
                        '_quantity': quantity,
                        '_email': email,
                        '_Image': productfood._Image,
                        '_NameProduct': productfood._NameProduct,
                        '_Price': productfood._Price,

                    }
                  
                    const dataNewCart =new cart(dataCartNew);
                    dataNewCart.save(function (err, data) {
                        if (err) {
                            console.log(err)
                            res.status(401).json({
                                success: false,
                                message: "Lỗi Server"
                            })
                        } else {
                            res.status(201).json({
                                "success": "true",
                                message: "Bỏ vào giỏ thành công"

                            })
                        }

                    });


                }
            })
        }
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
        var post_data = req.query; //Lấy dữ liệu từ client
        var ProductID = post_data.ProductID;
        var email = post_data.email;
        cart.remove({
            '_email': email,
            '_ProductID': ProductID
        }, function (err) {
            res.status(201).json({
                 success: true,
                 message: 'Xóa thành công'
                });
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
        var params_data = req.query;
        var email = params_data.Email;
      
        if(!email)
        {
            res.status(400).json({
                message:'email null'
            })
        }
        else{
        cart.find({ '_email': email }, function (err, dulieu) {
           
            res.status(201).json(dulieu);
        

        })
    }
    } catch (err) {
        res.status(500).json({
            success: false,
            message: err.message
        });
    }
})
cartRouter.post('/getEamil', async (req, res) => {

    var post_data = req.body;
    var email = post_data.email;
    cart.aggregate([
        { "$match": { "_email": email } },
        {
            $lookup: {
                from: 'users',
                localField: '_email',
                foreignField: '_email',
                as: 'user',

            }
        }
    ], function (err, data) {
        if (err) throw err;
        res.send(data);
    });
});
module.exports = cartRouter;

