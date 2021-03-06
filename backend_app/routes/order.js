var express = require('express');
var orderRouter = express.Router();
var cart = require('../model/Cart');
var User = require('../model/User');
var order = require('../model/Order')
var ProductFood = require('../model/ProductFood');
var orderList = require('../model/OrderList');
const { json } = require('express');
const mailer = require('../utils/mailer')

//New Order
orderRouter.post('/new-oder', async (req, res, next) => {
  try {
    var post_data = req.body;
    var email = post_data.email;
    var orderID = new Date().getTime();
    var createDate = `${new Date().getFullYear()}-${new Date().getMonth() + 1}-${new Date().getDate()}`;
    var total = post_data.total;
    var address = post_data.address;
    var phonenumber = post_data.phonenumber;
    var product = post_data.product
    var name= post_data.name
   
    if (!req.body) {
      res.status(401).json({
        message: 'Giỏ hàng trống'
      })
    }
    else {
      // var status = post_data.status;
      var orderdata = {
        '_email': email,
        '_OrderID': orderID,
        '_createDate': createDate,
        '_total': total,
        '_status': "Đang giao xử lý",
        '_address': address,
        '_phonenumber': phonenumber,
        '_product':product,
        '_name':name
        

      }
      mailer.sendMail(email,orderID,address,phonenumber,name,total,product)
      var dataNewOrder = await new order(orderdata);
      dataNewOrder.save(function (err) {
        if (err) {
          res.status(401).json({
            message: err.message
          })
        }
        else {
          //xóa cart
          cart.remove({
            '_email': email,
          }, function (err) {
            res.status(201).json({ message: "thêm vào thành công" });
          })
        }
      })
    }
  } catch (error) {
    console.log(error)
    res.status(500).json({
      success: false,
      message: error.message
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
        ], function (err, data) {
          if (err) throw err;
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
orderRouter.get('/listbyemail', async (req, res) => {
  try {
    const SortTime = { _createDay: -1 };
    const ListOrder = await order.find({ _email: req.query.email }).sort(SortTime);
    if (ListOrder == null) {
      res.status(400)
        .json({
          success: false,
          'message': 'email is not right'
        })
    }
    else (
      res.status(200)
        .json(ListOrder)
    )
  } catch (error) {
    res.status(500).json({
      success: false,
      'message': error.message
    });
  }
})

//Total
orderRouter.get('/getbyTotal', async (req, res) => {
  try {
    var params_data = req.params;
    var orderID = params_data.orderID;

    order.find({ '_total': 1 }, function (err, dulieu) {
      res.status(201).json(dulieu)
    });

  } catch (err) {
    res.status(500).json({
      success: false,
      message: err.message
    });
  }
})

module.exports = orderRouter;