// pages/about/userinfo/userinfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    Userentity:wx.getStorageSync('user'),
    user:wx.getStorageSync('user'),
    username:"",
    email:"",
    phone:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
    var _this=this
    wx.request({
      url: 'http://192.168.22.58:8762/member/getUser',
      data: {
        token: wx.getStorageSync('openid'),
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',//定义传到后台接受的是post方法还是get方法
      success: function (res) {
        console.log("调用API成功");
        console.log(res.data.data)
        _this.setData({
          Userentity: res.data.data
        })
      },

      fail: function (res) {
        console.log("调用API失败");
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },
  username(e) {
    this.setData({
      username: e.detail.value
    })
    
  },
  email(e) {
    this.setData({
      email: e.detail.value
    })
    
  },
  phone(e) {
    this.setData({
      phone: e.detail.value
    })
    
  },
  update: function () {
    var _this=this
    wx.request({
      url: 'http://localhost:8080/updateuser',
      data: {
        phone:_this.data.phone,
        email:_this.data.email,
        username:_this.data.username,
        id:_this.data.user.id
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',//定义传到后台接受的是post方法还是get方法
      success: function (res) {
        console.log("调用API成功");
        console.log(res.data.data)
        _this.setData({
          Userentity: res.data.data
        })
      },

      fail: function (res) {
        console.log("调用API失败");
      }
    })
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