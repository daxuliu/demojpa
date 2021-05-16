// pages/find/home/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:"",
    shuju:"",
    ad:null,
    user:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this
    console.log(options.id)
    this.setData({
      id:options.id
    })
    wx.request({
      url: 'http://localhost:8080/adopter',
      data: {
        id:options.id
      },
      method: 'post',
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          ad: res.data[0]
        })
        console.log("getuser")
        wx.request({
          url: 'http://localhost:8080/getuser',
          data: {
            id:  res.data[0].userid,
          },
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          method: 'post',//定义传到后台接受的是post方法还是get方法
          success: function (res) {
           
          that.setData({
              user:res.data
            })
            
            console.log( res)
          },
    
          fail: function (res) {
            console.log("调用API失败");
          }
        })



      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that=this;
    wx.request({
      url: 'http://192.168.22.58:8772/find/getOneInfo',
      data: {
        fid: that.data.id
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      method: 'Post',
      dataType: 'json',
      success: function(res) {
        console.log(res.data);
        that.setData({
          shuju: res.data
        })
      },
      fail: function(res) {
        console.log("调用失败");
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})