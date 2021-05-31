var express = require('express');
var orderRouter = express.Router();
var cart = require('../model/Cart');
var User = require('../model/User');
var order = require('../model/Order')
var ProductFood = require('../model/ProductFood');
var orderList = require('../model/OrderList');
const { json } = require('express');

//New Order
orderRouter.post('/new_oder', async (req, res, next) => {
  try {
    var post_data = req.body;
    var email = post_data.email;
    var orderID = new Date().getTime();
    var createDate = `${new Date().getFullYear()}-${new Date().getMonth() + 1}-${new Date().getDate()}`;
    var total = post_data.total;
    var status = post_data.status;
    var orderdata = {
      '_email': email,
      '_OrderID': orderID,
      '_createDate': createDate,
      '_total': total,
      '_status': status

    }
    var dataNewOrder = new order(orderdata);
    dataNewOrder.save(function (err) {
      res.status(201).json("thêm vào thành công")
    })
   
    //xóa cart
    cart.remove({
      '_email': email,
    }, function (err) {
      res.status(201).json({ success: true });
    })


  } catch (err) {
    res.status(500).json({
      success: false,
      message: err.message
    })
  }

});

//Lấy Thông tin theo mã order
orderRouter.get('/getOrderID/:orderID', async (req, res) => {
  try {
    var params_data = req.params;
    var orderID = params_data.orderID;
    order.findOne({ '_total': orderID }).count(function (err, number) {
      if (number == 0) {
        res.json({
          success: false,
          message: "OrderID not exist"
        });
      } else {
       
        order.aggregate([
          { "$match": { "_OrderID": orderID } },
          {
            $lookup: {
              from: 'ProductFood',
              localField: '_ProductID',
              foreignField: '_ProductID',
              as: 'ProductOrder',

            }
          }
        ],function (err,data) {
          if(err) throw err;
            res.send(data);
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

//Xem thông tin danh sách order theo người dùng
orderRouter.get('/list/:email', async (req, res) => {
  try {
    order.findOne({ '_email': email }).sort({_createDay:1},function(err,data){
      res.status(200).json({
        success:true,
        data:data
      })
    })
  } catch (err) {
      res.status(500).json({
          success: false,
          message: err.message
      });
  }
});

//Total
orderRouter.get('/getbyTotal', async (req, res) => {
  try {
    var params_data = req.params;
    var orderID = params_data.orderID;
    
    order.find({'_total':1}, function (err, dulieu) {
      res.status(201).json(dulieu)});
  
  } catch (err) {
    res.status(500).json({
      success: false,
      message: err.message
  });
  }
})

module.exports = orderRouter;